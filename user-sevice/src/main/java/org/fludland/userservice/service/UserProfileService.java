package org.fludland.userservice.service;

import org.fludland.userservcie.profile.CreateProfileDto;
import org.fludland.userservcie.profile.OriginalProfileDto;
import org.fludland.userservcie.profile.UpdateProfileDto;

public interface UserProfileService {
    CreateProfileDto createProfile(CreateProfileDto userProfile);
    OriginalProfileDto editProfile(Long userId, UpdateProfileDto userProfile);
    boolean deleteProfile(Long userId);
    OriginalProfileDto getProfileByUserId(Long userId);
}
