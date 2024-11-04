package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PostDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Integer userId;
    private final Integer mediaFileId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @JsonCreator
    public PostDto(@JsonProperty("id") Long id,
                   @JsonProperty("title") String title,
                   @JsonProperty("content") String content,
                   @JsonProperty("userId") Integer userId,
                   @JsonProperty("mediaFileId") Integer mediaFileId,
                   @JsonProperty("createdAt") LocalDateTime createdAt,
                   @JsonProperty("updatedAt") LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.mediaFileId = mediaFileId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonGetter
    public Long getId() {
        return id;
    }

    @JsonGetter
    public String getTitle() {
        return title;
    }

    @JsonGetter
    public String getContent() {
        return content;
    }

    @JsonGetter
    public Integer getUserId() {
        return userId;
    }

    @JsonGetter
    public Integer getMediaFileId() {
        return mediaFileId;
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
