package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditPostDto {
    private final String title;
    private final String content;

    @JsonCreator
    public EditPostDto(@JsonProperty("title") String title,
                       @JsonProperty("content") String content) {
        this.title = title;
        this.content = content;
    }

    @JsonGetter
    public String getTitle() {
        return title;
    }

    @JsonGetter
    public String getContent() {
        return content;
    }
}
