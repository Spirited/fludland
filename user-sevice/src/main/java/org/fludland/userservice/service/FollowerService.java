package org.fludland.userservice.service;

public interface FollowerService {
    void followToUser(Long userId, Long followerId);
    void unfollowFromUser(Long userId, Long followerId);
    void getFollowers(Long userId);

    /**
     * check method is user B already following user A
     * @param userId
     * @param followerId
     * @return
     */
    boolean isFollowing(Long userId, Long followerId);
}
