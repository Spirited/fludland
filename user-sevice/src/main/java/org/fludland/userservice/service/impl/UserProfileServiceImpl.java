package org.fludland.userservice.service.impl;

import org.fludland.userservcie.CreateProfileDto;
import org.fludland.userservice.entities.UserProfile;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(final UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public String createProfile(CreateProfileDto userProfile) {
        UserProfile userProfileEntity = new UserProfile();
        userProfileEntity.setUserId(userProfile.getUserId());
        userProfileEntity.setFirstName(userProfile.getFirstName());
        userProfileEntity.setLastName(userProfile.getLastName());
        userProfileEntity.setEmail(userProfile.getEmail());

        userProfileRepository.save(userProfileEntity);

        return "Success";
    }

    @Override
    public void editProfile(Integer profileId, CreateProfileDto userProfile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteProfile(Integer profileId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserProfile getProfile(Integer profileId) {
        return null;
    }
}
