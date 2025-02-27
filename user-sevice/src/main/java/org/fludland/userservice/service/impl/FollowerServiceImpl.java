package org.fludland.userservice.service.impl;

import org.fludland.userservice.entities.UserProfile;
import org.fludland.userservice.exceptions.ProfileNotFoundException;
import org.fludland.userservice.repository.FollowerRepository;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public FollowerServiceImpl(
            final FollowerRepository followerRepository,
            final UserProfileRepository userProfileRepository
    ) {
        this.followerRepository = followerRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void followToUser(Long userId, Long followerId) {

    }

    @Override
    public void unfollowFromUser(Long userId, Long followerId) {

    }

    @Override
    public void getFollowers(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    public boolean isFollowing(Long userId, Long followerId) {
        UserProfile profile = userProfileRepository.findByUserId(userId).orElseThrow(ProfileNotFoundException::new);
        return profile.getFollowers()
                .stream()
                .anyMatch(followers -> followers.getFollower().getUserId().equals(followerId));
    }
}
