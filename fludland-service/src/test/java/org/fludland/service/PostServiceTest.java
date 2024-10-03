package org.fludland.service;

import org.fludland.entities.Post;
import org.fludland.exceptions.PostNotFoundException;
import org.fludland.repositories.PostRepository;
import org.fludland.services.PostService;
import org.fludland.services.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PostServiceTest {
    private PostService postService;
    private PostRepository mockPostRepository;

    private static final Long POST_ID = 1L;

    @BeforeEach
    void setUp() {
        mockPostRepository = mock(PostRepository.class);
        postService = new PostServiceImpl(mockPostRepository);
    }

    @Test
    void test_create_new_post_expect_non_null_post() {
        when(mockPostRepository.save(any(Post.class))).thenReturn(createPost());
        PostDto postDto = postService.create(createPostDto());

        assertThat(postDto).isNotNull();
    }

    @Test
    void test_find_post_by_id_expect_non_null_post() {
        when(mockPostRepository.findById(POST_ID)).thenReturn(Optional.of(createPost()));
        PostDto postDto = postService.get(POST_ID);

        assertThat(postDto).isNotNull();
    }

    @Test
    void test_find_post_by_id_non_exists_in_db_expect_throws_exception() {
        when(mockPostRepository.findById(POST_ID)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.get(POST_ID));
    }

    @Test
    void test_update_post_by_id_non_exists_in_db_expect_throws_exception() {
        when(mockPostRepository.findById(POST_ID)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.update(POST_ID, null));
    }

    @Test
    void test_when_update_post_expects_not_null_results() {
        when(mockPostRepository.findById(any(Long.class))).thenReturn(Optional.of(createPost()));

        when(mockPostRepository.save(any(Post.class))).thenReturn(updatePost());

        PostDto update = postService.update(POST_ID, editPostDto());
        assertThat(update).isNotNull();
    }

    @Test
    void test_delete_post_by_id_non_exists_in_db_expect_throws_exception() {
        when(mockPostRepository.findById(POST_ID)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postService.delete(POST_ID));
    }

    @Test
    void test_delete_post_by_id_success_scenario() {
        when(mockPostRepository.existsById(any(Long.class))).thenReturn(Boolean.TRUE);
        when(mockPostRepository.findById(POST_ID)).thenReturn(Optional.empty());

        boolean deleted = postService.delete(POST_ID);

        assertThat(deleted).isTrue();
    }

    private static CreatePostDto createPostDto() {
        return new CreatePostDto("mockPostTitle", "Mock Content", 999);
    }

    private static EditPostDto editPostDto() {
        return new EditPostDto("mockNewPostTitle", "New Mock Content");
    }

    private static Post createPost() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Mock Title");
        post.setContent("Mock Content");
        post.setUserId(999);
        post.setCreatedAt(Timestamp.from(Instant.now()));

        return post;
    }

    private static Post updatePost() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("mockNewPostTitle");
        post.setContent("New Mock Content");
        post.setUserId(999);
        post.setCreatedAt(Timestamp.from(Instant.now()));

        return post;
    }

}
