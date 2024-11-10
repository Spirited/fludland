package org.fludland.repository;

import org.fludland.entities.Post;
import org.fludland.entities.Thumb;
import org.fludland.repositories.PostRepository;
import org.fludland.repositories.ThumbRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ThumbRepositoryTest extends AbstractDataIntegrationTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ThumbRepository thumbRepository;

    private static final int USER_ID = 1;

    @Test
    @Transactional
    void test_add_user_one_thumb_for_post() {
        Post post = postRepository.findById(1L).orElse(null);

        createNewThumbEntity(post, USER_ID);

        Post fetched = postRepository.findById(1L).orElse(null);
        assertThat(fetched).isNotNull();
        assert fetched != null;
        assertThat(fetched.getThumbs()).isNotNull();

        assertThat(fetched.getThumbs()).hasSize(1);
    }

    @Test
    @Transactional
    void test_add_thumb_from_two_users_for_post_expected_total_likes_two() {
        Post post = postRepository.findById(1L).orElse(null);

        createNewThumbEntity(post, USER_ID);

        createNewThumbEntity(post, 2);

        Post fetched = postRepository.findById(1L).orElse(null);
        assertThat(fetched).isNotNull();

        assert fetched != null;
        assertThat(fetched.getThumbs()).hasSize(2);
    }

    @Test
    @Transactional
    void test_total_likes_by_posts_expected_total_likes_three() {
        Post post = postRepository.findById(1L).orElse(null);

        createNewThumbEntity(post, USER_ID);
        createNewThumbEntity(post, 2);
        createNewThumbEntity(post, 3);

        long total = thumbRepository.countAllByPost(post);
        assertThat(total).isEqualTo(3);
    }


    @Transactional
    void createNewThumbEntity(Post post, int userId) {
        Thumb thumb = new Thumb();
        thumb.setUserId(userId);
        thumb.setPost(post);

        thumbRepository.saveAndFlush(thumb);
    }
}
