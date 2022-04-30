package mroki.api.com.blog.repository.impl;

import mroki.api.com.blog.dto.request.GetPostHomePageRequest;
import mroki.api.com.blog.model.*;
import mroki.api.com.blog.repository.PostRepositoryCustom;
import mroki.api.com.blog.repository.projection.PostHomePageProjection;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostHomePageProjection> findAllPost(GetPostHomePageRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PostHomePageProjection> criteriaQuery = criteriaBuilder.createQuery(PostHomePageProjection.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        Join<Post, Comment> joinComment = root.join(Post_.COMMENTS, JoinType.LEFT);
        Join<Post, UserLikedPost> postUserLikedPostJoin = root.join(Post_.USER_LIKED_POSTS, JoinType.LEFT);

        criteriaQuery.multiselect(
            root.get(Post_.ID),
            root.get(Post_.TITLE),
            root.get(Post_.CREATED_AT),
            criteriaBuilder.avg(joinComment.get(Comment_.RATE)),
            criteriaBuilder.count(postUserLikedPostJoin.get(UserLikedPost_.USER))
        );
        Predicate titleEqual = criteriaBuilder.like(
            criteriaBuilder.function("unaccent", String.class,
                criteriaBuilder.lower(root.get(Post_.TITLE))), "%" + StringUtils.stripAccents(request.getTitle()) + "%");
        criteriaQuery.groupBy(joinComment.get(Comment_.ID));
        criteriaQuery.groupBy(root.get(Post_.ID));
        Predicate publishEqual = criteriaBuilder.equal(root.get(Post_.PUBLISH), request.getPublish());
        List<Order> orderList = new ArrayList<>();

        if (request.getAsc()) {
            orderList.add(criteriaBuilder.asc(root.get(request.getSort())));
        } else {
            orderList.add(criteriaBuilder.desc(root.get(request.getSort())));
        }

        criteriaQuery.where(titleEqual, publishEqual).orderBy(orderList);
        return entityManager.createQuery(criteriaQuery)
            .setMaxResults(request.getSize())
            .setFirstResult(request.getPage() == 0 ? 0 : (request.getPage() * request.getSize()))
            .getResultList();

    }

    @Override
    public List<PostHomePageProjection> findPostsCustomForTest(GetPostHomePageRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PostHomePageProjection> criteriaQuery = criteriaBuilder.createQuery(PostHomePageProjection.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        Join<Post, Comment> joinComment = root.join(Post_.COMMENTS, JoinType.LEFT);

        criteriaQuery.multiselect(
            root.get(Post_.ID),
            root.get(Post_.TITLE),
            root.get(Post_.CREATED_AT),
            criteriaBuilder.avg(joinComment.get(Comment_.RATE))
        );
        Predicate titleEqual = criteriaBuilder.like(
            criteriaBuilder.lower(root.get(Post_.TITLE)), "%" + StringUtils.stripAccents(request.getTitle()) + "%");
        Predicate publishEqual = criteriaBuilder.equal(root.get(Post_.PUBLISH), request.getPublish());
        criteriaQuery.groupBy(joinComment.get(Comment_.ID));
        List<Order> orderList = new ArrayList<>();

        if (request.getAsc()) {
            orderList.add(criteriaBuilder.asc(root.get(request.getSort())));
        } else {
            orderList.add(criteriaBuilder.desc(root.get(request.getSort())));
        }

        criteriaQuery.where(titleEqual, publishEqual).orderBy(orderList);
        return entityManager.createQuery(criteriaQuery)
            .setMaxResults(request.getSize())
            .setFirstResult(request.getPage() == 0 ? 0 : (request.getPage() * request.getPage()))
            .getResultList();

    }
}
