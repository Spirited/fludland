package org.fludland.userservice.service;

import org.fludland.userservcie.CreateProfileDto;
import org.fludland.userservice.entities.UserProfile;

public interface UserProfileService {
    CreateProfileDto createProfile(CreateProfileDto userProfile);
    void editProfile(Integer profileId, CreateProfileDto userProfile);
    void deleteProfile(Integer profileId);
    UserProfile getProfile(Integer profileId);
}
