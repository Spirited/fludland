package org.fludland.userservice.controller;

import org.fludland.common.SuccessResponse;
import org.fludland.userservice.service.UserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFriendsController {
    private final UserFriendsService userFriendsService;

    @Autowired
    public UserFriendsController(UserFriendsService userFriendsService) {
        this.userFriendsService = userFriendsService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<String>> sendRequestToConnect() {
        return null;
    }
}
