package mroki.api.com.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLikedPost {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinTable(name = "postId")
    private Post post;
    @ManyToOne
    @JoinTable(name = "userId")
    private User user;
}
