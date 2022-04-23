package mroki.api.com.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
    indexes ={

    })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private Integer rate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne
    private User user;

}
