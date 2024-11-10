package org.fludland.service;

import org.fludland.entities.Post;
import org.fludland.exceptions.PostNotFoundException;
import org.fludland.repositories.PostRepository;
import org.fludland.repositories.ThumbRepository;
import org.fludland.services.ThumbService;
import org.fludland.services.impl.ThumbServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ThumbsServiceTest {
    private PostRepository mockPostRepository;
    private ThumbRepository mockThumbRepository;
    private ThumbService thumbService;

    private final long POST_ID = 1L;
    private final int USER_ID = 2;

    @BeforeEach
    void initialize() {
        mockPostRepository = mock(PostRepository.class);
        mockThumbRepository = mock(ThumbRepository.class);

        thumbService = new ThumbServiceImpl(mockPostRepository, mockThumbRepository);
    }

    @Test
    void test_put_thumbs_if_post_does_not_exist_expected_exception() {
        when(mockPostRepository.findById(POST_ID)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> thumbService.putPostThumb(POST_ID, USER_ID));
    }

    @Test
    void test_try_to_get_thumbs_if_post_does_not_exist_expected_exception() {
        when(mockPostRepository.findById(POST_ID)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> thumbService.getTotalPostThumbs(POST_ID));
    }

    @Test
    void test_get_total_post_thumbs_expected_not_zero_result() {
        Post post = new Post();
        post.setId(POST_ID);
        post.setUserId(USER_ID);

        when(mockPostRepository.findById(POST_ID)).thenReturn(Optional.of(post));

        when(mockThumbRepository.countAllByPost(post)).thenReturn(10L);

        assertThat(thumbService.getTotalPostThumbs(POST_ID)).isEqualTo(10L);
    }
}
