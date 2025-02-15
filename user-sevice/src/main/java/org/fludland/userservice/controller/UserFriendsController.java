package org.fludland.userservice.controller;

import org.fludland.common.SuccessResponse;
import org.fludland.userservcie.friends.FriendsDto;
import org.fludland.userservice.service.UserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles/friends")
public class UserFriendsController {
    private final UserFriendsService userFriendsService;

    @Autowired
    public UserFriendsController(UserFriendsService userFriendsService) {
        this.userFriendsService = userFriendsService;
    }

    //@PostMapping
    public ResponseEntity<SuccessResponse<String>> sendRequestToConnect() {
        return null;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<String>> addFriendToUser(
            @RequestParam("userId") final Long userId,
            @RequestParam("friendId") Long friendId
    ) {
        return ResponseEntity.ok(new SuccessResponse<>(userFriendsService.addUserFriend(userId, friendId)));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<FriendsDto>> getUserFriends(@RequestParam final Long userId) {
        return ResponseEntity.ok(new SuccessResponse<>(userFriendsService.getUserFriends(userId, 0, 0)));
    }
}
