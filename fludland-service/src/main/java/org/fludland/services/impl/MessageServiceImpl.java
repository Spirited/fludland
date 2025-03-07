package org.fludland.services.impl;

import org.fludland.entities.Message;
import org.fludland.repositories.MessageRepository;
import org.fludland.service.MessageDto;
import org.fludland.service.MessageRequest;
import org.fludland.service.MessageResponse;
import org.fludland.service.MessageStatus;
import org.fludland.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageResponse sendMessage(MessageRequest request) {
        Message message = new Message();
        message.setSource(request.getSource());
        message.setDestination(request.getDestination());
        message.setMessage(request.getMessage());
        message.setCreatedAt(LocalDateTime.now());
        message.setModifiedAt(LocalDateTime.now());
        
        Message savedMessage = messageRepository.save(message);
        return new MessageResponse(savedMessage.getId(), MessageStatus.SENT);
    }

    @Override
    public MessageDto getMessage(long messageId) {
        return messageRepository.findById(messageId)
            .map(this::mapToDto)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
    }

    @Override
    public List<MessageDto> getMessages(long sourceId, long destinationId) {
        return messageRepository.findAll().stream()
            .filter(m -> (m.getSource().equals(sourceId) && m.getDestination().equals(destinationId)) ||
                        (m.getSource().equals(destinationId) && m.getDestination().equals(sourceId)))
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private MessageDto mapToDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getSource(),
                message.getDestination(),
                message.getMessage(),
                message.getCreatedAt(),
                message.getModifiedAt()
        );
    }
}