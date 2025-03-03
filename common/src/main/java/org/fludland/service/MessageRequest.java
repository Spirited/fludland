package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest {
    private final Long source;
    private final Long destination;
    private final String message;

    @JsonCreator
    public MessageRequest(
            @JsonProperty("source")         final Long source,
            @JsonProperty("destination")    final Long destination,
            @JsonProperty("message")        final String message
    ) {
        this.source = source;
        this.destination = destination;
        this.message = message;
    }

    @JsonGetter
    public Long getSource() {
        return source;
    }

    @JsonGetter
    public Long getDestination() {
        return destination;
    }

    @JsonGetter
    public String getMessage() {
        return message;
    }
}
