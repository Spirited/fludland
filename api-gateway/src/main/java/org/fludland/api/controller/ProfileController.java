package org.fludland.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.fludland.api.dto.ProfileDto;
import org.fludland.api.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    private final ProfileService profileService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path = "/me")
    public ResponseEntity<ProfileDto> myProfile(HttpServletRequest request) {
        LOGGER.info("Fetching my profile...");
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok(profileService.getMyProfile(userId));
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<ProfileDto> userProfile(@PathVariable String userId) {
        return ResponseEntity.ok(profileService.getUserProfile("", Long.parseLong(userId)));
    }

}
