package org.fludland.services;

import org.fludland.common.ErrorResponse;
import org.fludland.service.CommentDto;
import org.fludland.service.CreateCommentDto;
import org.fludland.service.EditCommentDto;

import java.util.List;

public interface CommentService {
    CommentDto create(CreateCommentDto commentDto);
    CommentDto update(Long commentId, EditCommentDto commentDto);
    CommentDto get(Long id);
    List<CommentDto> getByPostId(Long postId);
    List<CommentDto> getByParentCommentId(Long parentCommentId);
    ErrorResponse delete(Long id);
}