package org.fludland.api.service;

import org.fludland.service.PostDto;

import java.util.List;

public interface FeedService {
    List<PostDto> myFeed();
    List<PostDto> recommendations();
}
