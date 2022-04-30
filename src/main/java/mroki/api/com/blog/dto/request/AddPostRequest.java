package mroki.api.com.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPostRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private MultipartFile file;
}
