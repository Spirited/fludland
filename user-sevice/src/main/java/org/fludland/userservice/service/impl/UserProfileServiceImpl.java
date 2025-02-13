package org.fludland.userservice.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.fludland.userservcie.profile.CreateProfileDto;
import org.fludland.userservcie.profile.OriginalProfileDto;
import org.fludland.userservcie.profile.UpdateProfileDto;
import org.fludland.userservice.entities.UserProfile;
import org.fludland.userservice.exceptions.ProfileByUserIdAlreadyException;
import org.fludland.userservice.exceptions.ProfileNotFoundException;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(final UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public CreateProfileDto createProfile(CreateProfileDto userProfile) {
        Optional<UserProfile> byUserId = userProfileRepository.findByUserId(userProfile.getUserId());

        if (byUserId.isPresent()) {
            throw new ProfileByUserIdAlreadyException();
        }

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
    public OriginalProfileDto editProfile(Long userId, UpdateProfileDto userProfile) {
        UserProfile profile = userProfileRepository.findByUserId(userId).orElseThrow(ProfileNotFoundException::new);

        if (StringUtils.isNotBlank(userProfile.getFirstName())) {
            profile.setFirstName(userProfile.getFirstName());
        }
        if (StringUtils.isNotBlank(userProfile.getLastName())) {
            profile.setLastName(userProfile.getLastName());
        }
        if (userProfile.getDateOfBirth() != null) {
            profile.setDateOfBirth(Date.valueOf(userProfile.getDateOfBirth()));
        }
        if (userProfile.getGender() != null) {
            profile.setGender(userProfile.getGender());
        }
        if (StringUtils.isNotBlank(userProfile.getEmail())) {
            profile.setEmail(userProfile.getEmail());
        }
        if (StringUtils.isNotBlank(userProfile.getPhoneNumber())) {
            profile.setPhone(userProfile.getPhoneNumber());
        }
        if (userProfile.getLogoId() != null) {
            profile.setLogoImageId(userProfile.getLogoId());
        }

        UserProfile saved = userProfileRepository.save(profile);
        return convertUserProfileToProfileDto(saved);
    }

    @Override
    public boolean deleteProfile(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId).orElseThrow(ProfileNotFoundException::new);
        userProfileRepository.delete(profile);

        return !userProfileRepository.findById(profile.getId()).isPresent();
    }

    @Override
    public OriginalProfileDto getProfileByUserId(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId).orElseThrow(ProfileNotFoundException::new);

        return convertUserProfileToProfileDto(profile);
    }

    private OriginalProfileDto convertUserProfileToProfileDto(UserProfile userProfile) {
        return new OriginalProfileDto(
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
