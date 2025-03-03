package org.fludland.services;

import org.fludland.service.MessageRequest;
import org.fludland.service.MessageResponse;

public interface MessageService {
    MessageResponse sendMessage(MessageRequest request);
}
