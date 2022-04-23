package mroki.api.com.blog.service.impl;

import mroki.api.com.blog.dto.request.SignUpRequest;
import mroki.api.com.blog.model.User;
import mroki.api.com.blog.repository.UserRepository;
import mroki.api.com.blog.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testSignUpSuccess(){
        SignUpRequest signUpRequest = SignUpRequest.builder()
            .username("username1")
            .firstname("hung")
            .lastname("hung")
            .password("hung")
            .email("hung@gmail.com")
            .gender("MALE")
            .dateOfBirth(LocalDate.now())
            .build();

        authService.signUp(signUpRequest);
    }

}
