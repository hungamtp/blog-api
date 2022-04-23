package mroki.api.com.blog.repository;

import mroki.api.com.blog.dto.request.GetPostHomePageRequest;
import mroki.api.com.blog.model.Post;
import mroki.api.com.blog.repository.projection.PostHomePageProjection;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostHomePageProjection> findPostsCustom(GetPostHomePageRequest request);
    List<PostHomePageProjection> findPostsCustomForTest(GetPostHomePageRequest request);
}
