package org.fludland.userservice.service.impl;

import org.fludland.userservcie.friends.FriendsDto;
import org.fludland.userservice.entities.Friends;
import org.fludland.userservice.entities.UserProfile;
import org.fludland.userservice.enums.FriendshipStatus;
import org.fludland.userservice.exceptions.ProfileNotFoundException;
import org.fludland.userservice.repository.UserFriendsRepository;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.UserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFriendsServiceImpl implements UserFriendsService {
    private final UserProfileRepository userProfileRepository;
    private final UserFriendsRepository userFriendsRepository;

    @Autowired
    public UserFriendsServiceImpl(
            final UserProfileRepository userProfileRepository,
            final UserFriendsRepository userFriendsRepository
    ) {
        this.userProfileRepository = userProfileRepository;
        this.userFriendsRepository = userFriendsRepository;
    }

    @Override
    public String addUserFriend(Long userId, Long friendId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId).orElseThrow(() -> new ProfileNotFoundException(userId.toString()));
        UserProfile friendProfile = userProfileRepository.findByUserId(friendId).orElseThrow(() -> new ProfileNotFoundException(friendId.toString()));

        Friends userFriend = new Friends();
        userFriend.setUser(userProfile);
        userFriend.setFriend(friendProfile);
        userFriend.setStatus(FriendshipStatus.FRIENDS);

        Friends friend = new Friends();
        friend.setUser(friendProfile);
        friend.setFriend(userProfile);
        friend.setStatus(FriendshipStatus.FRIENDS);

        userFriendsRepository.save(userFriend);
        userFriendsRepository.save(friend);

        return "Success";
    }

    @Override
    public void removeUserFriend(String userId, String friendId) {

    }

    @Override
    public FriendsDto getUserFriends(Long userId, int page, int pageSize) {
        UserProfile profile = userProfileRepository.findByUserId(userId).orElseThrow(() -> new ProfileNotFoundException(userId.toString()));
        List<Long> friends = profile.getFriends().stream().map(Friends::getUser).map(UserProfile::getUserId).toList();
        return new FriendsDto(friends);
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
