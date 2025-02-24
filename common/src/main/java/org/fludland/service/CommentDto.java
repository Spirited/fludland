package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDto {
    private final Long id;
    private final String content;
    private final Long postId;
    private final Long parentCommentId;
    private final List<Long> replyIds;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @JsonCreator
    public CommentDto(
            @JsonProperty("id")                 final Long id,
            @JsonProperty("content")            final String content,
            @JsonProperty("postId")             final Long postId,
            @JsonProperty("parentCommentId")    final Long parentCommentId,
            @JsonProperty("replyIds")           final List<Long> replyIds,
            @JsonProperty("createdAt")          final LocalDateTime createdAt,
            @JsonProperty("updatedAt")          final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.replyIds = replyIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonGetter
    public String getContent() {
        return content;
    }

    @JsonGetter
    public Long getPostId() {
        return postId;
    }

    @JsonGetter
    public Long getParentCommentId() {
        return parentCommentId;
    }

    @JsonGetter
    public List<Long> getReplyIds() {
        return replyIds;
    }

    @JsonGetter
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonGetter
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}