package mroki.api.com.blog.repository;

import mroki.api.com.blog.dto.request.GetPostHomePageRequest;
import mroki.api.com.blog.dto.request.CommonRequest;
import mroki.api.com.blog.repository.contraints.TestConstraint;
import mroki.api.com.blog.repository.projection.PostHomePageProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void findPostsTest() {
        GetPostHomePageRequest request = new GetPostHomePageRequest(TestConstraint.POST_TITLE, true);

        request.setPage(0);
        request.setSize(1);
        request.setSort("id");
        request.setAsc(true);
        List<PostHomePageProjection> result = postRepository.findPostsCustomForTest(request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get(0).getRate(), 5);

    }



}
