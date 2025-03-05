package org.fludland.services.impl;

import org.fludland.service.MessageDto;
import org.fludland.service.MessageRequest;
import org.fludland.service.MessageResponse;
import org.fludland.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public MessageResponse sendMessage(MessageRequest request) {
        return null;
    }

    @Override
    public MessageDto getMessage(long messageId) {
        return null;
    }

    @Override
    public List<MessageDto> getMessages(long sourceId, long destinationId) {
        return List.of();
    }
}
