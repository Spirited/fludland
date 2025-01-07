package org.fludland.userservice.service;

import org.fludland.userservice.entities.UserProfile;

public interface UserProfileService {
    void createProfile(UserProfile userProfile);
    void editProfile(Integer profileId, UserProfile userProfile);
    void deleteProfile(Integer profileId);
    UserProfile getProfile(Integer profileId);
}
