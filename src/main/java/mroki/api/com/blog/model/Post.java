package mroki.api.com.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")
    },
    indexes ={
        @Index(name = "title_index" , columnList = "title"),
        @Index(name = "publish_idx" , columnList = "publish")
    })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private LocalDate createdAt;
    private LocalDate editedAt;
    private Boolean publish;
    private Boolean isSlide;
    @Lob
    private byte[] image;
    @ManyToMany
    @JoinTable(name = "product_tag")
    private List<Tag> tags;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
