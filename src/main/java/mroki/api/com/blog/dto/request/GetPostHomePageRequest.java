package mroki.api.com.blog.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostHomePageRequest extends CommonRequest {
    private String title;
    private Boolean publish;

}
