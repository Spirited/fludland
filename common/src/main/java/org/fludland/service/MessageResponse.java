package org.fludland.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {
    private final Long messageId;
    private final MessageStatus status;

    @JsonCreator
    public MessageResponse(
            @JsonProperty("messageId")  final Long messageId,
            @JsonProperty("status")     final MessageStatus status) {
        this.messageId = messageId;
        this.status = status;
    }

    public Long getMessageId() {
        return messageId;
    }

    public MessageStatus getStatus() {
        return status;
    }
}
