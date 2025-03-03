package org.fludland.userservice.service;

import org.fludland.userservcie.friends.FriendsDto;

public interface FriendsService {
    String sendFriendRequest(Long userId, Long friendRequestId);
    String addUserFriend(Long userId, Long friendId);
    void removeUserFriend(String userId, String friendId);
    FriendsDto getUserFriends(Long userId, int page, int pageSize);
    void getUserFriendsTotal(String userId);
    void blockUserFriend(String userId, String friendId);
    void unblockUserFriend(String userId, String friendId);
    void recommendUserFriend(String userId);
    boolean isUserFriend(String userId, String friendId);
}
