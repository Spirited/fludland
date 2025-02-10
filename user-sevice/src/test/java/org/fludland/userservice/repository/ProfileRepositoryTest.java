package org.fludland.userservice.repository;

import org.fludland.userservcie.enums.Gender;
import org.fludland.userservice.entities.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SqlGroup(@Sql({"classpath:/cleanup.sql", "classpath:/data.sql"}))
class ProfileRepositoryTest extends AbstractDataIntegrationTest {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    void test_create_profile_expected_success_result() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(99L);
        userProfile.setFirstName("John");
        userProfile.setLastName("Doe");
        userProfile.setEmail("john@doe.com");
        userProfile = userProfileRepository.save(userProfile);

        assertThat(userProfile).isNotNull();
        assertThat(userProfile.getId()).isNotNull().isEqualTo(userProfile.getId());
        assertThat(userProfile.getUserId()).isEqualTo(userProfile.getUserId());
        assertThat(userProfile.getFirstName()).isEqualTo(userProfile.getFirstName());
        assertThat(userProfile.getLastName()).isEqualTo(userProfile.getLastName());
    }

    @Test
    void test_find_all_profiles_expected_one_result() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();

        assertThat(userProfiles).isNotNull();
        assertThat(userProfiles.size()).isEqualTo(1);
    }

    @Test
    void test_find_profile_by_id_expected_one_result() {
        Optional<UserProfile> profile = userProfileRepository.findById(1L);

        assertThat(profile).isNotNull();
        assertThat(profile.isPresent()).isTrue();

        UserProfile userProfile = profile.get();
        assertThat(userProfile.getId()).isEqualTo(1L);
        assertThat(userProfile.getFirstName()).isEqualTo("john");
        assertThat(userProfile.getLastName()).isEqualTo("smith");
        assertThat(userProfile.getEmail()).isEqualTo("john@smith.com");
        assertThat(userProfile.getUserId()).isEqualTo(42L);
        assertThat(userProfile.getDateOfBirth()).isEqualTo(Date.valueOf("1987-12-11"));
        assertThat(userProfile.getGender()).isEqualTo(Gender.MALE);
        assertThat(userProfile.getPhone()).isEqualTo("380971228367");
        assertThat(userProfile.getLogoImageId()).isEqualTo(13L);
    }

    @Test
    void test_delete_profile_expected_null_result_while_find_profile_by_id() {
        userProfileRepository.deleteById(1L);
        Optional<UserProfile> profile = userProfileRepository.findById(1L);
        assertThat(profile).isNotNull();
    }
}
