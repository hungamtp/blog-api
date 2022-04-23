package mroki.api.com.blog.service;


import mroki.api.com.blog.dto.request.SignUpRequest;

public interface UserService {
    Boolean signUp (SignUpRequest signUpRequest);
}
