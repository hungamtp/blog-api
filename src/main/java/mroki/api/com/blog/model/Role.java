package mroki.api.com.blog.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles",
		indexes ={
				@Index(name = "role_idx" , columnList = "name")
		}
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
	@Id
	@GeneratedValue
	@Column(name = "roleId")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "role")
	private Collection<User> users;

    public Role(Long id){
        this.id = id;
    }
}
