package org.fludland.sso.controller;

import org.fludland.sso.dtos.UserDetailsDto;
import org.fludland.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDetailsDto> getUser(@RequestParam(name = "token") String token) {
        return ResponseEntity.ok(userService.getUserDetails(token));
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserDetails(Long.parseLong(userId)));
    }
}
