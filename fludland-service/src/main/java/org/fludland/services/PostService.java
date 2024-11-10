package org.fludland.services;

import org.fludland.common.ErrorResponse;
import org.fludland.service.CreatePostDto;
import org.fludland.service.EditPostDto;
import org.fludland.service.PostDto;

import java.util.List;

public interface PostService {
    PostDto create(CreatePostDto postDto);
    PostDto update(Long postId, EditPostDto postDto);
    PostDto get(Long id);
    List<PostDto> getAllPostsByUserId(Integer userId);
    List<PostDto> getAll();
    ErrorResponse delete(Long id);
}
