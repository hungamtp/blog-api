package mroki.api.com.blog.security.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mroki.api.com.blog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String role;
    private Boolean isActivate;

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(user.getId(), user.getUsername(),
            user.getPassword(), user.getFirstName(), user.getLastName(),
            user.getDateOfBirth(), user.getGender(),
            user.getRole().getName(), user.getIsActivate()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActivate;
    }

}
