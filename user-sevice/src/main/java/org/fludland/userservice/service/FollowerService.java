package org.fludland.userservice.service;

public interface FollowerService {
    void followToUser(Long userId, Long followerId);
    void unfollowFromUser(Long userId, Long followerId);
    void getFollowers(Long userId);
}
