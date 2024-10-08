package org.fludland.services.impl;

import org.fludland.entities.Post;
import org.fludland.exceptions.PostNotFoundException;
import org.fludland.repositories.PostRepository;
import org.fludland.service.CreatePostDto;
import org.fludland.service.EditPostDto;
import org.fludland.service.PostDto;
import org.fludland.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);
    private static final String POST_NOT_FOUND = "Post %d not found";

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto create(CreatePostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUserId(postDto.getUserId());
        post.setModifiedAt(null);

        Post saved = postRepository.save(post);

        return convert(saved);
    }

    @Override
    public PostDto update(Long postId, EditPostDto postDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(String.format(POST_NOT_FOUND, postId)));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updated = postRepository.save(post);

        return convert(updated);
    }

    @Override
    public PostDto get(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(String.format(POST_NOT_FOUND, postId)));
        return convert(post);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new PostNotFoundException(POST_NOT_FOUND);
        }

        return postRepository.findById(id).orElse(null) == null;
    }

    private static PostDto convert(Post post) {
        return new PostDto(
                post.getId(), post.getTitle(), post.getContent(),
                post.getUserId(),
                post.getMediaFileId(),
                post.getCreatedAt() != null ? post.getCreatedAt().toInstant() : null,
                post.getModifiedAt() != null ? post.getModifiedAt().toInstant() : null
        );
    }
}
