package org.fludland.userservice.controller;

import org.fludland.common.SuccessResponse;
import org.fludland.userservcie.profile.CreateProfileDto;
import org.fludland.userservcie.profile.OriginalProfileDto;
import org.fludland.userservcie.profile.UpdateProfileDto;
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
    public ResponseEntity<SuccessResponse<CreateProfileDto>> createUserProfile(@RequestBody final CreateProfileDto createProfileDto) {
        return ResponseEntity.ok(new SuccessResponse<>(userProfileService.createProfile(createProfileDto)));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<OriginalProfileDto>> getUserProfileByUserId(
            @RequestParam("userId") final Long userId
    ) {
        return ResponseEntity.ok(new
                SuccessResponse<>(userProfileService.getProfileByUserId(userId)));
    }

    @PutMapping
    public ResponseEntity<SuccessResponse<OriginalProfileDto>> updateUserProfileUserId(
            @RequestParam("userId") final Long userId,
            @RequestBody            final UpdateProfileDto originalProfileDto
    ) {
        return ResponseEntity.ok(new SuccessResponse<>(userProfileService.editProfile(userId, originalProfileDto)));
    }

    @DeleteMapping
    public ResponseEntity<SuccessResponse<String>> deleteUserProfileUserId(@RequestParam("userId") final Long userId) {
        return ResponseEntity.ok(
                new SuccessResponse<>(userProfileService.deleteProfile(userId) ? "Deleted" : "Not Deleted")
        );
    }
}
