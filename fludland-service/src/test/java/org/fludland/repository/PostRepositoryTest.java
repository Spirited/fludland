package org.fludland.repository;

import org.fludland.entities.Post;
import org.fludland.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SqlGroup(@Sql({"classpath:/cleanup.sql"}))
class PostRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    void simple_test_create_post_expected_non_null_result() {
        Post post = new Post();
        post.setTitle("title");
        post.setContent("content");

        Post saved = postRepository.save(post);

        assertThat(saved).isNotNull();
    }

    @Test
    void test_save_new_post_expected_non_null_and_fill_out_result() {
        Post post = new Post();

        post.setTitle("title");
        post.setContent("content");
        post.setUserId(1);
        Post saved = postRepository.saveAndFlush(post);

        System.out.println(saved);

        assertThat(saved).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(post.getTitle());
        assertThat(saved.getContent()).isEqualTo(post.getContent());
        assertThat(saved.getUserId()).isEqualTo(1);
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(saved.getModifiedAt()).isNull();
    }

    @Test
    void test_update_post_expected_modified_at_not_null_result() {
        Post post = new Post();

        post.setTitle("title");
        post.setContent("content");
        post.setUserId(1);
        Post saved = postRepository.saveAndFlush(post);

        assertThat(saved).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(post.getTitle());
        assertThat(saved.getContent()).isEqualTo(post.getContent());
        assertThat(post.getUserId()).isEqualTo(1);
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(saved.getModifiedAt()).isNull();

        Post fetchedPost = postRepository.findById(1L).orElse(null);

        fetchedPost.setTitle("updated");
        fetchedPost.setModifiedAt(Timestamp.from(Instant.now())); // Just test! liqubase has not execute update_modified_column_procedure.sql!!!!

        Post modifiedPost = postRepository.saveAndFlush(fetchedPost);

        assertThat(modifiedPost).isNotNull();
        assertThat(modifiedPost.getTitle()).isEqualTo("updated");

        assertThat(modifiedPost.getCreatedAt()).isNotNull();
        assertThat(modifiedPost.getModifiedAt()).isNotNull();
    }
}
