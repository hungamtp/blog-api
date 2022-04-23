package mroki.api.com.blog.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String gender;
    private LocalDate dateOfBirth;
}
