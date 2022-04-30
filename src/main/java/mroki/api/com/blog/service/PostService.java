package mroki.api.com.blog.service;

import mroki.api.com.blog.dto.common.PageDTO;
import mroki.api.com.blog.dto.request.AddPostRequest;
import mroki.api.com.blog.dto.request.GetPostHomePageRequest;
import mroki.api.com.blog.model.Post;
import mroki.api.com.blog.repository.projection.PostHomePageProjection;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PageDTO findAllPost(GetPostHomePageRequest request);
    Post addPost(AddPostRequest request) throws IOException;
}
