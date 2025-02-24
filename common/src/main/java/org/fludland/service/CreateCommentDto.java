package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCommentDto {
    private final String content;
    private final Long postId;
    private final Long parentCommentId;

    @JsonCreator
    public CreateCommentDto(
            @JsonProperty("content")            final String content,
            @JsonProperty("postId")             final Long postId,
            @JsonProperty("parentCommentId")    final Long parentCommentId
    ) {
        this.content = content;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
    }

    public String getContent() {
        return content;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }
}