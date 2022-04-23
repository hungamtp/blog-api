package mroki.api.com.blog.dto.common;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private String errorCode;
    private Object data;
}
