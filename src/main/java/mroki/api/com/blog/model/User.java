package mroki.api.com.blog.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(	name = "users",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email")
		},
		indexes ={
			@Index(name = "user_role_idx" , columnList = "roleId"),
				@Index(name = "user_username_idx" , columnList = "username"),
				@Index(name = "user_isActivate_idx" , columnList = "isActivate"),
				@Index(name = "user_email_idx" , columnList = "email"),
				@Index(name = "user_isVerifiedEmail_idx" , columnList = "isVerifiedEmail"),
		})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue
	private Long id;

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
    private String gender;
    private Boolean isVerifiedEmail = false;
    private Boolean isActivate = true;
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "roleId")
	private Role role;



}
