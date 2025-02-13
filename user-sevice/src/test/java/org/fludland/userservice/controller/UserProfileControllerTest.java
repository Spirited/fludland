package org.fludland.userservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.fludland.common.SuccessResponse;
import org.fludland.userservcie.profile.CreateProfileDto;
import org.fludland.userservcie.enums.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserProfileControllerTest extends AbstractWebIntegrationTest {
    private static final String USERNAME_PARAM_NAME = "userId";
    private static final String USER_ID = "42";
    private static final String CONTENT_TYPE = "application/json";

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
                                .contentType(CONTENT_TYPE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SuccessResponse<CreateProfileDto> successResponse = asSingleObject(
                content,
                new TypeReference<SuccessResponse<CreateProfileDto>>() {}
        );

        assertThat(successResponse).isNotNull();

        CreateProfileDto profileDto = successResponse.getData();

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
        String content = mockMvc.perform(get("/profiles").param("userId", "42").contentType(CONTENT_TYPE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SuccessResponse<CreateProfileDto> successResponse = asSingleObject(
                content,
                new TypeReference<SuccessResponse<CreateProfileDto>>() {}
        );

        assertThat(successResponse).isNotNull();

        CreateProfileDto profileDto = successResponse.getData();
        assertThat(profileDto).isNotNull();
        assertThat(profileDto.getUserId()).isEqualTo(42L);
        assertThat(profileDto.getFirstName()).isEqualTo("john");
        assertThat(profileDto.getLastName()).isEqualTo("smith");
        assertThat(profileDto.getDateOfBirth()).isEqualTo(LocalDate.of(1987, 12, 11));
        assertThat(profileDto.getGender()).isEqualTo(Gender.MALE);
        assertThat(profileDto.getPhoneNumber()).isEqualTo("380971228367");
        assertThat(profileDto.getEmail()).isEqualTo("john@smith.com");
    }

    @Test
    void test_delete_user_profile_expected_success_result() throws Exception {
        String content = mockMvc.perform(
                        delete("/profiles")
                                .param(USERNAME_PARAM_NAME, USER_ID)
                                .contentType(CONTENT_TYPE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SuccessResponse<String> successResponse = asSingleObject(
                content,
                new TypeReference<SuccessResponse<String>>() {}
        );

        assertThat(successResponse).isNotNull();
        assertThat(successResponse.getData()).isNotNull();
        assertThat(successResponse.getData()).isEqualTo("Deleted");
    }

}
