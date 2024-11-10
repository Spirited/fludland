package org.fludland.services.impl;

import org.fludland.entities.Post;
import org.fludland.entities.Thumb;
import org.fludland.exceptions.PostNotFoundException;
import org.fludland.repositories.PostRepository;
import org.fludland.repositories.ThumbRepository;
import org.fludland.services.ThumbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ThumbServiceImpl implements ThumbService {
    private final PostRepository postRepository;
    private final ThumbRepository thumbRepository;

    private static final String POST_NOT_FOUND = "Post %d not found";
    private static final Logger LOGGER = LoggerFactory.getLogger(ThumbServiceImpl.class);

    @Autowired
    public ThumbServiceImpl(PostRepository postRepository, ThumbRepository thumbRepository) {
        this.postRepository = postRepository;
        this.thumbRepository = thumbRepository;
    }

    @Transactional
    @Override
    public void putPostThumb(long postId, int userId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format(POST_NOT_FOUND, postId)));

        boolean removed = post.getThumbs().removeIf(thumb -> thumb.getUserId() == userId);

        if (!removed) {

            Thumb thumb = new Thumb();
            thumb.setPost(post);
            thumb.setUserId(userId);

            LOGGER.info("Putting users thumb for post: postId={}, userId={}", postId, userId);
            thumbRepository.save(thumb);
        } else {
            LOGGER.info("Deleting users thumb for post: postId={}, userId={}", postId, userId);
            postRepository.save(post);
        }
    }

    @Override
    public long getTotalPostThumbs(long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new PostNotFoundException(String.format(POST_NOT_FOUND, postId)));

        return thumbRepository.countAllByPost(post);
    }
}
