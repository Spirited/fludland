package org.fludland.userservice.service.impl;

import org.fludland.userservice.repository.UserFriendsRepository;
import org.fludland.userservice.service.UserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFriendsServiceImpl implements UserFriendsService {
    private final UserFriendsRepository userFriendsRepository;

    @Autowired
    public UserFriendsServiceImpl(UserFriendsRepository userFriendsRepository) {
        this.userFriendsRepository = userFriendsRepository;
    }

    @Override
    public void addUserFriend(String userId, String friendId) {

    }

    @Override
    public void removeUserFriend(String userId, String friendId) {

    }

    @Override
    public void getUserFriends(String userId, int page, int pageSize) {

    }

    @Override
    public void getUserFriendsTotal(String userId) {

    }

    @Override
    public void blockUserFriend(String userId, String friendId) {

    }

    @Override
    public void unblockUserFriend(String userId, String friendId) {

    }

    @Override
    public void recommendUserFriend(String userId) {

    }

    @Override
    public boolean isUserFriend(String userId, String friendId) {
        return false;
    }
}
