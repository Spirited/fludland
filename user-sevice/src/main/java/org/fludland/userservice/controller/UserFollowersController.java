package org.fludland.userservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles/followers")
public class UserFollowersController {
    public void followToUser(Long userId, Long followerId) {

    }

    public void unfollowFromUser(Long userId, Long followerId) {}

    public void getFollowers(Long userId) {}
}
