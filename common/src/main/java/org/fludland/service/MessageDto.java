package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class MessageDto {
    private final long id;
    private final long source;
    private final long destination;
    private final String message;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @JsonCreator
    public MessageDto(
            @JsonProperty("id")             final long id,
            @JsonProperty("source")         final long source,
            @JsonProperty("destination")    final long destination,
            @JsonProperty("message")        final String message,
            @JsonProperty("createdAt")      final LocalDateTime createdAt,
            @JsonProperty("createdAt")      final LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.message = message;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public long getId() {
        return id;
    }

    public long getSource() {
        return source;
    }

    public long getDestination() {
        return destination;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
