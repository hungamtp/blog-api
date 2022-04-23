package mroki.api.com.blog.security.service;

import java.util.Optional;


import mroki.api.com.blog.model.User;
import mroki.api.com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> option = userRepository.findByUsername(username);
        if(option.isPresent() == false) {
            throw new UsernameNotFoundException("Username not found");
        }
        return UserDetailsImpl.build(option.get());
    }



}
