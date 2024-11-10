package org.fludland.controllers;

import org.fludland.common.ErrorResponse;
import org.fludland.service.CreatePostDto;
import org.fludland.service.EditPostDto;
import org.fludland.service.PostDto;
import org.fludland.services.PostService;
import org.fludland.services.ThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    private final PostService postService;
    private final ThumbService thumbService;

    @Autowired
    public PostController(PostService postService, ThumbService thumbService) {
        this.postService = postService;
        this.thumbService = thumbService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostDto createPostDto) {
        return ResponseEntity.ok(postService.create(createPostDto));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postService.get(Long.parseLong(id)));
    }

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(postService.getAllPostsByUserId(Integer.parseInt(userId)));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> editPost(@PathVariable String id, @RequestBody EditPostDto editPostDto) {
        return ResponseEntity.ok(postService.update(Long.parseLong(id), editPostDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ErrorResponse> deletePost(@PathVariable String id) {
        return ResponseEntity.ok(postService.delete(Long.parseLong(id)));
    }

    @PostMapping("/{postId}/users/{userId}/thumbs")
    public void putThumbs(@PathVariable String postId, @PathVariable String userId) {
        thumbService.putPostThumb(Long.parseLong(postId), Integer.parseInt(userId));
    }

    @GetMapping("/{postId}/thumbs/total")
    public Long totalPostThumbs(@PathVariable String postId) {
        return thumbService.getTotalPostThumbs(Long.parseLong(postId));
    }
}
