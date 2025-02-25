package org.fludland.service;

import org.fludland.entities.Comment;
import org.fludland.entities.Post;
import org.fludland.repositories.CommentRepository;
import org.fludland.repositories.PostRepository;
import org.fludland.services.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Post testPost;
    private Comment testComment;

    @BeforeEach
    void setUp() {
        testPost = new Post();
        testPost.setId(1L);
        
        testComment = new Comment();
        testComment.setId(1L);
        testComment.setContent("Test comment");
        testComment.setPost(testPost);
    }

    @Test
    void create_Success() {
        CreateCommentDto createDto = new CreateCommentDto("Test comment", 1L, null);

        when(postRepository.findById(1L)).thenReturn(Optional.of(testPost));
        when(commentRepository.save(any(Comment.class))).thenReturn(testComment);

        CommentDto result = commentService.create(createDto);

        assertNotNull(result);
        assertEquals("Test comment", result.getContent());
        assertEquals(1L, result.getPostId());
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void update_Success() {
        EditCommentDto editDto = new EditCommentDto("Updated content");

        Comment updatedComment = new Comment();
        updatedComment.setId(1L);
        updatedComment.setContent("Updated content");
        updatedComment.setPost(testPost);

        when(commentRepository.findById(1L)).thenReturn(Optional.of(testComment));
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);

        CommentDto result = commentService.update(1L, editDto);

        assertNotNull(result);
        assertEquals("Updated content", result.getContent());
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void get_Success() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(testComment));

        CommentDto result = commentService.get(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test comment", result.getContent());
    }

    @Test
    void getByPostId_Success() {
        List<Comment> comments = Arrays.asList(testComment);
        when(commentRepository.findByPostId(1L)).thenReturn(comments);

        List<CommentDto> results = commentService.getByPostId(1L);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Test comment", results.get(0).getContent());
    }

    @Test
    void getByParentCommentId_Success() {
        Comment replyComment = new Comment();
        replyComment.setId(2L);
        replyComment.setContent("Reply comment");
        replyComment.setPost(testPost);
        replyComment.setParentComment(testComment);

        List<Comment> replies = Arrays.asList(replyComment);
        when(commentRepository.findByParentCommentId(1L)).thenReturn(replies);

        List<CommentDto> results = commentService.getByParentCommentId(1L);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Reply comment", results.get(0).getContent());
    }

    @Test
    void delete_Success() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(testComment));
        doNothing().when(commentRepository).delete(any(Comment.class));

        var response = commentService.delete(1L);

        assertNotNull(response);
        verify(commentRepository).delete(testComment);
    }
}