package org.fludland.controllers;

import org.fludland.service.CreatePostDto;
import org.fludland.service.EditPostDto;
import org.fludland.service.PostDto;
import org.fludland.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostDto createPostDto) {
        return ResponseEntity.ok(postService.create(createPostDto));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String id) {
        throw new UnsupportedOperationException();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> editPost(@PathVariable String id, @RequestBody EditPostDto editPostDto) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable String id) {
        return ResponseEntity.ok(true);
    }
}
