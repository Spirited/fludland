package org.fludland.userservice.service.impl;

import org.fludland.userservice.repository.FollowerRepository;
import org.fludland.userservice.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;

    @Autowired
    public FollowerServiceImpl(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    @Override
    public void followToUser(Long userId, Long followerId) {

    }

    @Override
    public void unfollowFromUser(Long userId, Long followerId) {

    }

    @Override
    public void getFollowers(Long userId) {

    }
}
