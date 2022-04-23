package mroki.api.com.blog.repository;

import mroki.api.com.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post , Long>,PostRepositoryCustom {
}
