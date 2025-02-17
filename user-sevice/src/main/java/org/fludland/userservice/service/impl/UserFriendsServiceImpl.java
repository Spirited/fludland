package org.fludland.userservice.service.impl;

import org.fludland.userservcie.friends.FriendDto;
import org.fludland.userservcie.friends.FriendsDto;
import org.fludland.userservice.entities.Friends;
import org.fludland.userservice.entities.UserProfile;
import org.fludland.userservcie.enums.FriendshipStatus;
import org.fludland.userservice.exceptions.ProfileNotFoundException;
import org.fludland.userservice.repository.UserFriendsRepository;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.UserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String sendFriendRequest(Long userId, Long friendRequestId) {
        UserProfile userProfile = getUserProfile(userId);
        UserProfile friendProfile = getUserProfile(friendRequestId);

        Friends userFriend = new Friends();
        userFriend.setUser(userProfile);
        userFriend.setFriend(friendProfile);
        userFriend.setStatus(FriendshipStatus.REQUESTED);

        Friends friend = new Friends();
        friend.setUser(friendProfile);
        friend.setFriend(userProfile);
        friend.setStatus(FriendshipStatus.PENDING);

        userFriendsRepository.save(userFriend);
        userFriendsRepository.save(friend);

        return "Success";
    }

    @Override
    public String addUserFriend(Long userId, Long friendId) {
        UserProfile userProfile = getUserProfile(userId);
        UserProfile friendProfile = getUserProfile(friendId);

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
        return new FriendsDto(
                profile.getFriends()
                        .stream()
                        .map(f -> new FriendDto(f.getUser().getUserId(), f.getStatus()))
                        .toList());

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

    private UserProfile getUserProfile(Long userId) {
        return userProfileRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException(userId.toString()));
    }
}
