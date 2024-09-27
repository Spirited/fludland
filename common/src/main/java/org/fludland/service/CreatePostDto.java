package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePostDto {
    private final String title;
    private final String content;
    private final int userId;

    @JsonCreator
    public CreatePostDto(@JsonProperty("title") String title,
                         @JsonProperty("content") String content,
                         @JsonProperty("userId") int userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
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
    public int getUserId() {
        return userId;
    }
}
