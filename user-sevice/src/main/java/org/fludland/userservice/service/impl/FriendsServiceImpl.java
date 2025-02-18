package org.fludland.userservice.service.impl;

import org.fludland.userservcie.friends.FriendDto;
import org.fludland.userservcie.friends.FriendsDto;
import org.fludland.userservice.entities.Friends;
import org.fludland.userservice.entities.UserProfile;
import org.fludland.userservcie.enums.FriendshipStatus;
import org.fludland.userservice.exceptions.ProfileNotFoundException;
import org.fludland.userservice.repository.FriendsRepository;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsServiceImpl implements FriendsService {
    private final UserProfileRepository userProfileRepository;
    private final FriendsRepository friendsRepository;

    @Autowired
    public FriendsServiceImpl(
            final UserProfileRepository userProfileRepository,
            final FriendsRepository friendsRepository
    ) {
        this.userProfileRepository = userProfileRepository;
        this.friendsRepository = friendsRepository;
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

        friendsRepository.save(userFriend);
        friendsRepository.save(friend);

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

        friendsRepository.save(userFriend);
        friendsRepository.save(friend);

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
