package mroki.api.com.blog.repository.projection;

import lombok.*;
import mroki.api.com.blog.model.Comment;

import java.time.LocalDate;
import java.util.List;

@Value
public class PostHomePageProjection {
    Long id;
    String title;
    LocalDate createdAt;
    Double rate;
}
