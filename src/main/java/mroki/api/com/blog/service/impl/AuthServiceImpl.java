package mroki.api.com.blog.service.impl;


import lombok.AllArgsConstructor;
import mroki.api.com.blog.constants.EntityName;
import mroki.api.com.blog.constants.ErrorCode;
import mroki.api.com.blog.dto.request.JwtResponse;
import mroki.api.com.blog.dto.request.LoginRequest;
import mroki.api.com.blog.dto.request.SignUpRequest;
import mroki.api.com.blog.exception.custom.ApiRequestException;
import mroki.api.com.blog.model.Role;
import mroki.api.com.blog.model.User;
import mroki.api.com.blog.repository.RoleRepository;
import mroki.api.com.blog.repository.UserRepository;
import mroki.api.com.blog.security.jwt.JwtUtils;
import mroki.api.com.blog.security.service.UserDetailsImpl;
import mroki.api.com.blog.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userDetails.getRole().equalsIgnoreCase("ROLE_ADMIN_LOCKED") ||
            userDetails.getRole().equalsIgnoreCase("ROLE_USER_LOCKED"))
        {
            throw new ApiRequestException(ErrorCode.USER_BLOCKED);
        }
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(),
            userDetails.getUsername(), roles.get(0));
    }

    @Override
    public JwtResponse signUp(SignUpRequest signUpRequest) {
        Optional<User> user = userRepository.findByUsername(signUpRequest.getUsername());
        if(user.isPresent()){
            throw new IllegalStateException(EntityName.USER + ErrorCode.EXIST);
        }
        user = userRepository.findByEmail(signUpRequest.getEmail());
        if(user.isPresent()){
            throw new IllegalStateException(EntityName.USER + ErrorCode.EXIST);
        }
        User savedUser = modelMapper.map(signUpRequest , User.class);
        savedUser.setPassword(encoder.encode(signUpRequest.getPassword()));
        savedUser.setRole(new Role(1L));

        userRepository.save(savedUser);

        return login(new LoginRequest(signUpRequest.getUsername() , signUpRequest.getPassword()));
    }


}
