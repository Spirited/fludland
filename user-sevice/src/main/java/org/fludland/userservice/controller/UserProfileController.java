package org.fludland.userservice.controller;

import org.fludland.userservcie.CreateProfileDto;
import org.fludland.userservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(final UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<CreateProfileDto> createUserProfile(@RequestBody final CreateProfileDto createProfileDto) {
        return ResponseEntity.ok(userProfileService.createProfile(createProfileDto));
    }
}
