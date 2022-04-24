package mroki.api.com.blog.dto.request;

import lombok.*;
import mroki.api.com.blog.constants.ErrorCode;
import mroki.api.com.blog.constants.FieldName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SignUpRequest {
    @Size(max = 16 , min = 8 , message = ErrorCode.USERNAME_LENGTH)
    private String username;
    private String email;
    @Size(max = 16 , min = 8)
    @NotNull
    private String password;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String gender;
    @NotNull
    private LocalDate dateOfBirth;
}
