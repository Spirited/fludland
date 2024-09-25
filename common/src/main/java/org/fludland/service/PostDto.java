package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class PostDto {
    private final String id;
    private final String title;
    private final String content;
    private final long userId;
    private final long mediaFileId;
    private final Instant createdAt;
    private final Instant updatedAt;

    @JsonCreator
    public PostDto(@JsonProperty("id") String id,
                   @JsonProperty("title") String title,
                   @JsonProperty("content") String content,
                   @JsonProperty("userId") long userId,
                   @JsonProperty("mediaFileId") long mediaFileId,
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
    public String getId() {
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
    public long getUserId() {
        return userId;
    }

    @JsonGetter
    public long getMediaFileId() {
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
