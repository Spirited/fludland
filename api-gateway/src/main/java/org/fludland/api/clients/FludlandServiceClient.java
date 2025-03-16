package org.fludland.api.clients;

import org.fludland.common.ErrorResponse;
import org.fludland.service.CreatePostDto;
import org.fludland.service.EditPostDto;
import org.fludland.service.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "fludland-service", url = "${api.fludland.host}")
public interface FludlandServiceClient {
    @PostMapping("/posts")
    PostDto createPost(@RequestBody CreatePostDto createPostDto);

    @GetMapping("/posts/{id}")
    PostDto getPostById(@PathVariable String id);

    @GetMapping("/posts/users/{userId}")
    List<PostDto> getPostByUserId(@PathVariable String userId);

    @GetMapping("/posts")
    List<PostDto> getAllPosts();

    @PutMapping("/posts/{id}")
    PostDto editPost(@PathVariable String id, @RequestBody EditPostDto editPostDto);

    @DeleteMapping("/posts/{id}")
    ErrorResponse deletePost(@PathVariable String id);
}
