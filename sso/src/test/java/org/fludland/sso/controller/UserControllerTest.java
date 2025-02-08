package org.fludland.sso.controller;

import org.fludland.sso.dtos.UserDetailsDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends AbstractWebIntegrationTest {
    private static final Long USER_ID = 1L;
    private static final String USER_NAME = "testuser";

    @Test
    void test_get_user_by_token_expected_success_result() throws Exception {
        String response = mockMvc.perform(get("/users").param("token", TEST_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDetailsDto userDetailsDto = asSingleObject(response, UserDetailsDto.class);
        assertThat(userDetailsDto).isNotNull();
        assertThat(userDetailsDto.getUserId()).isEqualTo(USER_ID);
        assertThat(userDetailsDto.getUsername()).isEqualTo(USER_NAME);
    }

    @Test
    void test_get_user_by_id_expected_success_result() throws Exception {
        String content = mockMvc.perform(get("/users/{userId}", USER_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDetailsDto userDetailsDto = asSingleObject(content, UserDetailsDto.class);
        assertThat(userDetailsDto).isNotNull();
        assertThat(userDetailsDto.getUserId()).isEqualTo(USER_ID);
        assertThat(userDetailsDto.getUsername()).isEqualTo(USER_NAME);
    }

    private static final String TEST_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBY2Nlc3MtVG9rZW4iLCJpc3MiOiJGbHVkbGFuZCIsImV4cCI6MTczOTEyOTI1OCwidXNlcmlkIjoxLCJpYXQiOjE3MzkwNDI4NTgsInVzZXJuYW1lIjoidGVzdHVzZXIifQ.UTgvEZG4LUWsqVY5GfUC-8h2Vq5kLh2gY4dHS3Z05II";
}
