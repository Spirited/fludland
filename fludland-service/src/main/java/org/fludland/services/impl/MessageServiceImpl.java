package org.fludland.services.impl;

import org.fludland.service.MessageRequest;
import org.fludland.service.MessageResponse;
import org.fludland.services.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public MessageResponse sendMessage(MessageRequest request) {
        return null;
    }
}
