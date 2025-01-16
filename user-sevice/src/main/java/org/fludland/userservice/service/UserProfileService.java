package org.fludland.userservice.service;

import org.fludland.userservcie.CreateProfileDto;
import org.fludland.userservcie.OriginalProfileDto;

public interface UserProfileService {
    CreateProfileDto createProfile(CreateProfileDto userProfile);
    void editProfile(Integer profileId, CreateProfileDto userProfile);
    void deleteProfile(Integer profileId);
    OriginalProfileDto getProfileByUserId(Long userId);
}
