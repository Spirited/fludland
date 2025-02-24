package org.fludland.controllers;

import org.fludland.common.ErrorResponse;
import org.fludland.service.CommentDto;
import org.fludland.service.CreateCommentDto;
import org.fludland.service.EditCommentDto;
import org.fludland.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CreateCommentDto createCommentDto) {
        return ResponseEntity.ok(commentService.create(createCommentDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.get(id));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getByPostId(postId));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<CommentDto>> getCommentsByParentId(@PathVariable Long parentId) {
        return ResponseEntity.ok(commentService.getByParentCommentId(parentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long id,
            @RequestBody EditCommentDto editCommentDto) {
        return ResponseEntity.ok(commentService.update(id, editCommentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ErrorResponse> deleteComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.delete(id));
    }
}