package mroki.api.com.blog.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonRequest {
    Integer page;
    Integer size;
    String sort;
    Boolean asc;
}
