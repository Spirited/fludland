package org.fludland.services;

import org.fludland.service.MessageDto;
import org.fludland.service.MessageRequest;
import org.fludland.service.MessageResponse;

import java.util.List;

public interface MessageService {
    MessageResponse sendMessage(MessageRequest request);
    MessageDto getMessage(long messageId);
    List<MessageDto> getMessages(long sourceId, long destinationId);
}
