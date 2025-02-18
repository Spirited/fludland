package org.fludland.userservice.controller;

import org.fludland.common.SuccessResponse;
import org.fludland.userservcie.friends.FriendsDto;
import org.fludland.userservice.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles/friends")
public class UserFriendsController {
    private final FriendsService friendsService;

    @Autowired
    public UserFriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping("/request")
    public ResponseEntity<SuccessResponse<String>> sendRequestToConnect(
            @RequestParam("userId") final Long userId,
            @RequestParam("friendId") Long friendId
    ) {
        return ResponseEntity.ok(new SuccessResponse<>(friendsService.sendFriendRequest(userId, friendId)));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<String>> addFriendToUser(
            @RequestParam("userId") final Long userId,
            @RequestParam("friendId") Long friendId
    ) {
        return ResponseEntity.ok(new SuccessResponse<>(friendsService.addUserFriend(userId, friendId)));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<FriendsDto>> getUserFriends(@RequestParam final Long userId) {
        return ResponseEntity.ok(new SuccessResponse<>(friendsService.getUserFriends(userId, 0, 0)));
    }
}
