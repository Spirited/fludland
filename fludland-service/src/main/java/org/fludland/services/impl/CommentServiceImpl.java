package org.fludland.services.impl;

import org.fludland.common.ErrorCodes;
import org.fludland.common.ErrorResponse;
import org.fludland.entities.Comment;
import org.fludland.entities.Post;
import org.fludland.exceptions.CommentNotFoundException;
import org.fludland.exceptions.PostNotFoundException;
import org.fludland.repositories.CommentRepository;
import org.fludland.repositories.PostRepository;
import org.fludland.service.CommentDto;
import org.fludland.service.CreateCommentDto;
import org.fludland.service.EditCommentDto;
import org.fludland.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto create(CreateCommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
        comment.setPost(post);
        
        if (commentDto.getParentCommentId() != null) {
            Comment parentComment = commentRepository.findById(commentDto.getParentCommentId())
                    .orElseThrow(() -> new CommentNotFoundException("Parent comment not found"));
            comment.setParentComment(parentComment);
            parentComment.addReply(comment);
        }
        
        return convert(commentRepository.save(comment));
    }

    @Override
    public CommentDto update(Long commentId, EditCommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));
        
        comment.setContent(commentDto.getContent());
        return convert(commentRepository.save(comment));
    }

    @Override
    public CommentDto get(Long id) {
        return convert(commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found")));
    }

    @Override
    public List<CommentDto> getByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public List<CommentDto> getByParentCommentId(Long parentCommentId) {
        return commentRepository.findByParentCommentId(parentCommentId).stream()
                .map(this::convert)
                .toList();
    }

    @Transactional
    @Override
    public ErrorResponse delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
        
        // Remove this comment from parent's replies if it has a parent
        if (comment.getParentComment() != null) {
            comment.getParentComment().removeReply(comment);
        }
        
        // Clear any replies to maintain referential integrity
        List<Comment> replies = new ArrayList<>(comment.getReplies());
        replies.forEach(reply -> {
            reply.setParentComment(null);
            commentRepository.save(reply);
        });
        
        comment.setReplies(new ArrayList<>());
        commentRepository.delete(comment);
        return new ErrorResponse(ErrorCodes.SUCCESS_ERROR_CODE);
    }

    private CommentDto convert(Comment comment) {
        if (comment == null) {
            return null;
        }
        
        return new CommentDto(
            comment.getId(),
            comment.getContent(),
            comment.getPost().getId(),
            comment.getParentComment() != null ? comment.getParentComment().getId() : null,
            comment.getReplies().stream().map(Comment::getId).toList(),
            comment.getCreatedAt(),
            comment.getUpdatedAt()
        );
    }
}