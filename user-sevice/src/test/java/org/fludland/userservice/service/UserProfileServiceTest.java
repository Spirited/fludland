package org.fludland.userservice.service;

import org.fludland.userservcie.profile.CreateProfileDto;
import org.fludland.userservcie.profile.OriginalProfileDto;
import org.fludland.userservcie.profile.UpdateProfileDto;
import org.fludland.userservcie.enums.Gender;
import org.fludland.userservice.entities.UserProfile;
import org.fludland.userservice.exceptions.AssignedUserIdToProfileException;
import org.fludland.userservice.exceptions.ProfileByUserIdAlreadyException;
import org.fludland.userservice.exceptions.ProfileNotFoundException;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.impl.UserProfileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserProfileServiceTest {
    private UserProfileRepository mockUserProfileRepository;
    private UserProfileService userProfileService;

    @BeforeEach
    void init() {
        mockUserProfileRepository = mock(UserProfileRepository.class);
        userProfileService = new UserProfileServiceImpl(mockUserProfileRepository);
    }

    @Test
    void test_find_user_by_user_id_9999_expected_throws_exception() {
        when(mockUserProfileRepository.findByUserId(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProfileNotFoundException.class, () -> userProfileService.getProfileByUserId(9999L));
    }

    @Test
    void test_create_user_profile_expected_success_result() {
        LocalDate birthday = LocalDate.of(2000, 1, 1);
        CreateProfileDto createProfileDto = new CreateProfileDto(
                42L, "test", "lastname",
                birthday, Gender.MALE, "123456789", "test@test.com", 101L
        );

        UserProfile profile = new UserProfile();
        profile.setId(1L);
        profile.setUserId(42L);
        profile.setFirstName("test");
        profile.setLastName("lastname");
        profile.setGender(Gender.MALE);
        profile.setDateOfBirth(Date.valueOf(birthday));
        profile.setPhone("123456789");
        profile.setEmail("test@test.com");
        profile.setLogoImageId(101L);

        when(mockUserProfileRepository.findByUserId(anyLong())).thenReturn(Optional.empty());
        when(mockUserProfileRepository.save(any())).thenReturn(profile);

        CreateProfileDto created = userProfileService.createProfile(createProfileDto);
        assertThat(created).isNotNull();
        assertThat(created.getUserId()).isEqualTo(createProfileDto.getUserId());
        assertThat(created.getFirstName()).isEqualTo(createProfileDto.getFirstName());
        assertThat(created.getLastName()).isEqualTo(createProfileDto.getLastName());
        assertThat(created.getGender()).isEqualTo(createProfileDto.getGender());
        assertThat(created.getDateOfBirth()).isEqualTo(createProfileDto.getDateOfBirth());
        assertThat(created.getPhoneNumber()).isEqualTo(createProfileDto.getPhoneNumber());
        assertThat(created.getLogoId()).isEqualTo(createProfileDto.getLogoId());
    }

    @Test
    void test_create_user_profile_when_user_id_already_exists_expected_throws_exception() {
        CreateProfileDto createProfileDto = new CreateProfileDto(
                42L, "test", "lastname",
                null, Gender.MALE, "123456789", "test@test.com", 101L
        );

        when(mockUserProfileRepository.findByUserId(anyLong())).thenReturn(Optional.of(new UserProfile()));

        assertThrows(ProfileByUserIdAlreadyException.class, () -> userProfileService.createProfile(createProfileDto));
    }

    @Test
    void test_update_user_profile_email_only_expected_success_result() {
        LocalDate birthday = LocalDate.of(2000, 1, 1);

        UserProfile fetchedProfile = generateFetchedUserProfile();

        UpdateProfileDto updateProfileDto = new UpdateProfileDto(
                null,
                null,
                null,
                null,
                null,
                "updated.email@gmail.com",
                null);

        UserProfile updatedProfile = new UserProfile();
        updatedProfile.setId(1L);
        updatedProfile.setUserId(42L);
        updatedProfile.setFirstName("test");
        updatedProfile.setLastName("lastname");
        updatedProfile.setGender(Gender.MALE);
        updatedProfile.setDateOfBirth(Date.valueOf(birthday));
        updatedProfile.setPhone("123456789");
        updatedProfile.setEmail("test@test.com");
        updatedProfile.setLogoImageId(101L);

        when(mockUserProfileRepository.findByUserId(anyLong())).thenReturn(Optional.of(fetchedProfile));
        when(mockUserProfileRepository.save(any())).thenReturn(updatedProfile);

        OriginalProfileDto originalProfileDto = userProfileService.editProfile(42L, updateProfileDto);
        assertThat(originalProfileDto).isNotNull();
        assertThat(originalProfileDto.getUserId()).isEqualTo(fetchedProfile.getUserId());
        assertThat(originalProfileDto.getFirstName()).isEqualTo(fetchedProfile.getFirstName());
        assertThat(originalProfileDto.getLastName()).isEqualTo(fetchedProfile.getLastName());
        assertThat(originalProfileDto.getGender()).isEqualTo(fetchedProfile.getGender());
        assertThat(originalProfileDto.getDateOfBirth()).isEqualTo(fetchedProfile.getDateOfBirth().toLocalDate());
        assertThat(originalProfileDto.getPhoneNumber()).isEqualTo(fetchedProfile.getPhone());
        assertThat(originalProfileDto.getEmail()).isEqualTo(updatedProfile.getEmail());
    }

    @Test
    void test_update_user_profile_expected_success_result() {
        UserProfile fetchedProfile = generateFetchedUserProfile();

        UpdateProfileDto updateProfileDto = generateUpdateUserProfileDtoFemale();

        UserProfile updatedProfile = generateUpdateUserProfile();

        when(mockUserProfileRepository.findByUserId(anyLong())).thenReturn(Optional.of(fetchedProfile));
        when(mockUserProfileRepository.save(any())).thenReturn(updatedProfile);

        OriginalProfileDto originalProfileDto = userProfileService.editProfile(42L, updateProfileDto);
        assertThat(originalProfileDto).isNotNull();
        assertThat(originalProfileDto.getUserId()).isEqualTo(updatedProfile.getUserId());
        assertThat(originalProfileDto.getFirstName()).isEqualTo(updatedProfile.getFirstName());
        assertThat(originalProfileDto.getLastName()).isEqualTo(updatedProfile.getLastName());
        assertThat(originalProfileDto.getGender()).isEqualTo(updatedProfile.getGender());
        assertThat(originalProfileDto.getDateOfBirth()).isEqualTo(updatedProfile.getDateOfBirth().toLocalDate());
        assertThat(originalProfileDto.getPhoneNumber()).isEqualTo(updatedProfile.getPhone());
        assertThat(originalProfileDto.getEmail()).isEqualTo(updatedProfile.getEmail());
    }

    @Test
    void test_update_user_profile_with_null_value_user_id_expected_throws_exception() {
        UpdateProfileDto updateProfileDto = generateUpdateUserProfileDtoMale();
        assertThrows(AssignedUserIdToProfileException.class, () -> userProfileService.editProfile(null, updateProfileDto));
    }

    @Test
    void test_try_to_delete_user_profile_by_user_id_which_does_not_exist_expected_throws_exception() {
        when(mockUserProfileRepository.findByUserId(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProfileNotFoundException.class, () -> userProfileService.deleteProfile(42L));
    }

    @Test
    void test_delete_user_profile_expected_success_result() {
        when(mockUserProfileRepository.findByUserId(anyLong())).thenReturn(Optional.of(generateFetchedUserProfile()));
        when(mockUserProfileRepository.findById(anyLong())).thenReturn(Optional.empty());
        boolean result = userProfileService.deleteProfile(42L);
        assertThat(result).isTrue();
    }

    private static UserProfile generateFetchedUserProfile() {
        return generateUserProfile(
                1L, 42L, "test", "lastname", Gender.MALE,
                LocalDate.of(2000, 1, 1), "123456789", "test@test.com", 101L
        );
    }

    private static UserProfile generateUpdateUserProfile() {

        return generateUserProfile(1L, 42L, "santa", "clauth",Gender.MALE,
                LocalDate.of(1899, 11, 11), "0987654321", "updated.email@gmail.com", 999L);
    }

    private static UserProfile generateUserProfile(
            final long id,
            final long userId,
            String firstName,
            String lastName,
            Gender gender,
            LocalDate birthday,
            String phone,
            String email,
            long logoImageId
    ) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        userProfile.setUserId(userId);
        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);
        userProfile.setGender(gender);
        userProfile.setDateOfBirth(Date.valueOf(birthday));
        userProfile.setPhone(phone);
        userProfile.setEmail(email);
        userProfile.setLogoImageId(logoImageId);

        return userProfile;
    }

    private static UpdateProfileDto generateUpdateUserProfileDtoFemale() {
        return generateUpdateProfileDto(
                "santa",
                "clauth",
                LocalDate.of(1899, 11, 11),
                Gender.FEMALE,
                "0987654321",
                "updated.email@gmail.com",
                999L);
    }

    private static UpdateProfileDto generateUpdateUserProfileDtoMale() {
        return generateUpdateProfileDto(
                "santa",
                "clauth",
                LocalDate.of(1899, 11, 11),
                Gender.MALE,
                "0987654321",
                "updated.email@gmail.com",
                999L);
    }

    private static UpdateProfileDto generateUpdateProfileDto(
            String firstName, String lastName, LocalDate birthDay, Gender gender, String phoneNumber, String email, long logoImageId
    ) {
        return new UpdateProfileDto(
                firstName,
                lastName,
                birthDay,
                gender,
                phoneNumber,
                email,
                logoImageId);
    }
}
