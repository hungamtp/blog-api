package mroki.api.com.blog.service;


import mroki.api.com.blog.dto.request.JwtResponse;
import mroki.api.com.blog.dto.request.LoginRequest;
import mroki.api.com.blog.dto.request.SignUpRequest;

public interface AuthService {

    JwtResponse login(LoginRequest loginRequest);
    JwtResponse signUp(SignUpRequest signUpRequest);
}
