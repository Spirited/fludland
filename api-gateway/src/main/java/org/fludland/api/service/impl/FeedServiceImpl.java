package org.fludland.api.service.impl;

import org.fludland.api.clients.FludlandServiceClient;
import org.fludland.api.service.FeedService;
import org.fludland.service.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {
    private final FludlandServiceClient fludlandServiceClient;

    @Autowired
    public FeedServiceImpl(final FludlandServiceClient fludlandServiceClient) {
        this.fludlandServiceClient = fludlandServiceClient;
    }

    @Override
    public List<PostDto> myFeed() {
        //TODO
        return List.of();
    }

    @Override
    public List<PostDto> recommendations() {
        //TODO
        return List.of();
    }
}
