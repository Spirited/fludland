package org.fludland.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.fludland.service.CommentDto;
import org.fludland.service.CreateCommentDto;
import org.fludland.service.EditCommentDto;
import org.fludland.service.PostDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerTest extends AbstractWebIntegrationTest {

    @Test
    void test_create_comment_success() throws Exception {
        // First create a post to attach the comment to
        var post = createTestPost();
        
        CreateCommentDto createCommentDto = new CreateCommentDto("Test comment content", post.getId(), null);

        var result = mockMvc.perform(post("/comments")
                .content(Objects.requireNonNull(asJsonString(createCommentDto)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        CommentDto commentDto = asSingleObject(result, CommentDto.class);
        assertNotNull(commentDto);
        assertEquals("Test comment content", commentDto.getContent());
        assertEquals(post.getId(), commentDto.getPostId());
    }

    @Test
    void test_get_comment_by_id() throws Exception {
        var post = createTestPost();
        var comment = createTestComment(post.getId());

        var result = mockMvc.perform(get("/comments/" + comment.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        CommentDto commentDto = asSingleObject(result, CommentDto.class);
        assertNotNull(commentDto);
        assertEquals(comment.getId(), commentDto.getId());
    }

    @Test
    void test_get_comments_by_post_id() throws Exception {
        var post = createTestPost();
        createTestComment(post.getId());
        createTestComment(post.getId());

        var result = mockMvc.perform(get("/comments/posts/" + post.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<CommentDto> comments = asSingleObject(result, new TypeReference<List<CommentDto>>() {});
        assertEquals(2, comments.size());
    }

    @Test
    void test_update_comment() throws Exception {
        var post = createTestPost();
        var comment = createTestComment(post.getId());

        EditCommentDto editCommentDto = new EditCommentDto("Updated content");

        var result = mockMvc.perform(put("/comments/" + comment.getId())
                .content(Objects.requireNonNull(asJsonString(editCommentDto)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        CommentDto updatedComment = asSingleObject(result, CommentDto.class);
        assertEquals("Updated content", updatedComment.getContent());
    }

    @Test
    void test_delete_comment() throws Exception {
        var post = createTestPost();
        var comment = createTestComment(post.getId());

        mockMvc.perform(delete("/comments/" + comment.getId()))
                .andExpect(status().isOk());

        // Verify comment is deleted
        mockMvc.perform(get("/comments/" + comment.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_get_replies_by_parent_comment() throws Exception {
        var post = createTestPost();
        var parentComment = createTestComment(post.getId());
        
        // Create reply comments
        CreateCommentDto replyDto = new CreateCommentDto("Reply comment", post.getId(), parentComment.getId());

        mockMvc.perform(post("/comments")
                .content(Objects.requireNonNull(asJsonString(replyDto)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        var result = mockMvc.perform(get("/comments/parent/" + parentComment.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<CommentDto> replies = asSingleObject(result, new TypeReference<List<CommentDto>>() {});
        assertEquals(1, replies.size());
    }

    private PostDto createTestPost() {
        return new PostDto(1L, "Test post", "Test content", 1, null, LocalDateTime.now(), LocalDateTime.now());
    }

    private CommentDto createTestComment(Long postId) throws Exception {
        CreateCommentDto createCommentDto = new CreateCommentDto("Test comment", postId, null);

        var result = mockMvc.perform(post("/comments")
                .content(Objects.requireNonNull(asJsonString(createCommentDto)))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        return asSingleObject(result, CommentDto.class);
    }

}