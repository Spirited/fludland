package org.fludland.services;

import org.fludland.service.CreatePostDto;
import org.fludland.service.EditPostDto;
import org.fludland.service.PostDto;

public interface PostService {
    PostDto create(CreatePostDto postDto);
    PostDto update(Long postId, EditPostDto postDto);
    PostDto get(Long id);
    boolean delete(Long id);
}
