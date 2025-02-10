package org.fludland.userservice.service;

import org.fludland.userservcie.CreateProfileDto;
import org.fludland.userservcie.OriginalProfileDto;
import org.fludland.userservcie.UpdateProfileDto;

public interface UserProfileService {
    CreateProfileDto createProfile(CreateProfileDto userProfile);
    OriginalProfileDto editProfile(Long userId, UpdateProfileDto userProfile);
    void deleteProfile(Integer profileId);
    OriginalProfileDto getProfileByUserId(Long userId);
}
