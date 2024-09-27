package org.fludland.repository;

import org.fludland.entities.Post;
import org.fludland.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

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
    void test_save_new_post_expected_non_null_and_fullfiled_result() {
        Post post = new Post();
        post.setTitle("title");
        post.setContent("content");
        Post saved = postRepository.save(post);

        System.out.println(saved);

        assertThat(saved).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(post.getTitle());
        assertThat(saved.getContent()).isEqualTo(post.getContent());
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(saved.getModifiedAt()).isNull();
    }
}
