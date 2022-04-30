package mroki.api.com.blog.controllers.user;

import lombok.AllArgsConstructor;
import mroki.api.com.blog.dto.common.ResponseDTO;
import mroki.api.com.blog.dto.request.AddPostRequest;
import mroki.api.com.blog.dto.request.GetPostHomePageRequest;
import mroki.api.com.blog.model.Post;
import mroki.api.com.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllPost(@RequestBody @Valid GetPostHomePageRequest request){
        ResponseDTO response = new ResponseDTO();
        response.setData(postService.findAllPost(request));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> addPost(@ModelAttribute AddPostRequest request ) throws IOException {
        Post post = postService.addPost(request );
        return ResponseEntity.ok().body(post);
    }

}
