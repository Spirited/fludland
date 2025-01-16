package org.fludland.userservice.controller;

import org.fludland.userservcie.CreateProfileDto;
import org.fludland.userservcie.OriginalProfileDto;
import org.fludland.userservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{userId}")
    public ResponseEntity<OriginalProfileDto> getUserProfile(@PathVariable final Long userId) {
        return ResponseEntity.ok(userProfileService.getProfileByUserId(userId));
    }
}
