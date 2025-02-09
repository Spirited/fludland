package org.fludland.userservice.controller;

import org.fludland.userservcie.CreateProfileDto;
import org.fludland.userservcie.enums.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserProfileControllerTest extends AbstractWebIntegrationTest {
    @Test
    void test_create_user_profile_expected_success_result() throws Exception {
        CreateProfileDto createProfileDto = new CreateProfileDto(
                100L,
                "John",
                "Snow",
                LocalDate.of(1990, 11, 12),
                Gender.MALE,
                "0321123344",
                "john.snow@ukr.net",
                12L);

        String content = mockMvc.perform(
                        post("/profiles")
                                .content(Objects.requireNonNull(asJsonString(createProfileDto)))
                                .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CreateProfileDto profileDto = asSingleObject(content, CreateProfileDto.class);
        assertThat(profileDto).isNotNull();
        assertThat(profileDto.getUserId()).isEqualTo(100L);
        assertThat(profileDto.getFirstName()).isEqualTo("John");
        assertThat(profileDto.getLastName()).isEqualTo("Snow");
        assertThat(profileDto.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 11, 12));
        assertThat(profileDto.getGender()).isEqualTo(Gender.MALE);
        assertThat(profileDto.getPhoneNumber()).isEqualTo("0321123344");
        assertThat(profileDto.getEmail()).isEqualTo("john.snow@ukr.net");
    }

    @Test
    void test_try_to_get_userprofile_by_user_id_expected_success_result() throws Exception {
        String content = mockMvc.perform(get("/profiles/{userId}", 42L).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CreateProfileDto singleObject = asSingleObject(content, CreateProfileDto.class);
        assertThat(singleObject).isNotNull();
        assertThat(singleObject.getUserId()).isEqualTo(42L);
        assertThat(singleObject.getFirstName()).isEqualTo("john");
        assertThat(singleObject.getLastName()).isEqualTo("smith");
        assertThat(singleObject.getDateOfBirth()).isEqualTo(LocalDate.of(1987, 12, 11));
        assertThat(singleObject.getGender()).isEqualTo(Gender.MALE);
        assertThat(singleObject.getPhoneNumber()).isEqualTo("380971228367");
        assertThat(singleObject.getEmail()).isEqualTo("john@smith.com");
    }
    
}
