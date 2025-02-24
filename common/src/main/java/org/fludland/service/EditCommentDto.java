package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditCommentDto {
    private final String content;

    @JsonCreator
    public EditCommentDto(@JsonProperty("content") final String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}