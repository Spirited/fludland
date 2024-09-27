package org.fludland.services;

import org.fludland.entities.Post;
import org.fludland.exceptions.PostNotFoundException;
import org.fludland.repositories.PostRepository;
import org.fludland.service.CreatePostDto;
import org.fludland.service.EditPostDto;
import org.fludland.service.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

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

        Post saved = postRepository.save(post);

        return convert(saved);
    }

    @Transactional
    @Override
    public PostDto update(Long postId, EditPostDto postDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updated = postRepository.save(post);

        return convert(updated);
    }

    @Override
    public PostDto get(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
        return convert(post);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));

        postRepository.deleteById(id);

        return postRepository.findById(id).orElse(null) == null;
    }

    private static PostDto convert(Post post) {
        return new PostDto(
                post.getId(), post.getTitle(), post.getContent(),
                post.getUserId(),
                post.getMediaFileId(),
                post.getCreatedAt().toInstant(),
                post.getModifiedAt()
        );
    }
}
