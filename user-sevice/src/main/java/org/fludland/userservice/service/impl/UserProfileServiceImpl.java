package org.fludland.userservice.service.impl;

import org.fludland.userservcie.CreateProfileDto;
import org.fludland.userservice.entities.UserProfile;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(final UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public CreateProfileDto createProfile(CreateProfileDto userProfile) {
        UserProfile userProfileEntity = new UserProfile();

        userProfileEntity.setUserId(userProfile.getUserId());
        userProfileEntity.setFirstName(userProfile.getFirstName());
        userProfileEntity.setLastName(userProfile.getLastName());
        userProfileEntity.setDateOfBirth(
                userProfile.getDateOfBirth() != null
                        ? Date.valueOf(userProfile.getDateOfBirth())
                        : null
        );
        userProfileEntity.setGender(userProfile.getGender());
        userProfileEntity.setEmail(userProfile.getEmail());
        userProfileEntity.setPhone(userProfile.getPhoneNumber());
        userProfileEntity.setLogoImageId(userProfile.getLogoId());

        UserProfile saved = userProfileRepository.save(userProfileEntity);

        return convertUserProfileToCreateProfileDto(saved);
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

    private CreateProfileDto convertUserProfileToCreateProfileDto(UserProfile userProfile) {
        return new CreateProfileDto(
                userProfile.getUserId(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getDateOfBirth()
                        != null ? userProfile.getDateOfBirth().toLocalDate()
                        : null,
                userProfile.getGender(),
                userProfile.getPhone(),
                userProfile.getEmail(),
                userProfile.getLogoImageId()
        );
    }
}
