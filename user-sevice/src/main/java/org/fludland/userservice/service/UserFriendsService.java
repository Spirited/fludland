package org.fludland.userservice.service;

public interface UserFriendsService {
    void addUserFriend(String userId, String friendId);
    void removeUserFriend(String userId, String friendId);
    void getUserFriends(String userId, int page, int pageSize);
    void getUserFriendsTotal(String userId);
    void blockUserFriend(String userId, String friendId);
    void unblockUserFriend(String userId, String friendId);
    void recommendUserFriend(String userId);
    boolean isUserFriend(String userId, String friendId);
}
