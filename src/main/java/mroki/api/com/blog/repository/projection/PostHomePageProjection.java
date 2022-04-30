package mroki.api.com.blog.repository.projection;

import lombok.*;
import mroki.api.com.blog.model.Comment;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostHomePageProjection {
    private Long id;
    private String title;
    private LocalDate createdAt;
    private Long countLike;
//    private

}
