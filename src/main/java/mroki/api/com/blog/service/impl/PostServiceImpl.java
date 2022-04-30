package mroki.api.com.blog.service.impl;

import lombok.AllArgsConstructor;
import mroki.api.com.blog.dto.common.PageDTO;
import mroki.api.com.blog.dto.request.AddPostRequest;
import mroki.api.com.blog.dto.request.GetPostHomePageRequest;
import mroki.api.com.blog.model.Post;
import mroki.api.com.blog.repository.PostRepository;
import mroki.api.com.blog.repository.projection.PostHomePageProjection;
import mroki.api.com.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;


    @Override
    public PageDTO findAllPost(GetPostHomePageRequest request) {
        var data = postRepository.findAllPost(request);
        return PageDTO.builder().page(request.getPage())
            .data(data)
            .elements(data.size())
            .build();
    }

    @Override
    public Post addPost(AddPostRequest request ) throws IOException {
        Post savedPost = new Post();
        savedPost.setTitle(request.getTitle());
        savedPost.setImage(request.getFile().getBytes());
        savedPost = postRepository.save(savedPost);
        return savedPost;
    }
}
