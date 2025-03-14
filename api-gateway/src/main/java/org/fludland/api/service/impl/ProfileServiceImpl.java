package org.fludland.api.service.impl;

import org.fludland.api.clients.FileServiceClient;
import org.fludland.api.clients.SSOClient;
import org.fludland.api.clients.UserServiceClient;
import org.fludland.api.dto.ProfileDto;
import org.fludland.api.service.ProfileService;
import org.fludland.file.ImageDto;
import org.fludland.sso.dtos.UserDetailsDto;
import org.fludland.userservcie.profile.OriginalProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final SSOClient ssoClient;
    private final UserServiceClient userService;
    private final FileServiceClient fileServiceClient;

    @Autowired
    public ProfileServiceImpl(
            final SSOClient ssoClient,
            final UserServiceClient userService,
            final FileServiceClient fileServiceClient
    ) {
        this.ssoClient = ssoClient;
        this.userService = userService;
        this.fileServiceClient = fileServiceClient;
    }

    @Override
    public ProfileDto getMyProfile(Long userId) {
        UserDetailsDto user = ssoClient.getUser(userId);
        OriginalProfileDto profile = userService.getProfile(user.getUserId().toString());

        String profileUrl = null;
        if (profile.getLogoId() != null) {
            ImageDto image = fileServiceClient.getImage(profile.getLogoId());
            profileUrl = image.getUrl();
        }

        return new ProfileDto(
                user.getUserId(),
                user.getUsername(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getDateOfBirth(),
                profile.getGender(),
                profile.getPhoneNumber(),
                profile.getEmail(),
                null,
                Collections.singletonList(profileUrl)
        );
    }

    @Override
    public ProfileDto getUserProfile(String token, Long id) {
        return null;
    }
}
