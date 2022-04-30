package mroki.api.com.blog.dto.common;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private int page;
    private int elements;
    private Object data;
}
