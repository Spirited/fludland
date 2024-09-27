package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class PostDto {
    private final Integer id;
    private final String title;
    private final String content;
    private final Integer userId;
    private final Integer mediaFileId;
    private final Instant createdAt;
    private final Instant updatedAt;

    @JsonCreator
    public PostDto(@JsonProperty("id") Integer id,
                   @JsonProperty("title") String title,
                   @JsonProperty("content") String content,
                   @JsonProperty("userId") Integer userId,
                   @JsonProperty("mediaFileId") Integer mediaFileId,
                   @JsonProperty("createdAt") Instant createdAt,
                   @JsonProperty("updatedAt") Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.mediaFileId = mediaFileId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @JsonGetter
    public Integer getId() {
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
    public Instant getCreatedAt() {
        return createdAt;
    }
    @JsonGetter
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
