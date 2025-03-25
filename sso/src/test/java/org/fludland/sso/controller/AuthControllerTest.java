package org.fludland.sso.controller;

import org.fludland.common.ErrorResponse;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.LoginRequestDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.fludland.common.ErrorType.WRONG_LOGIN_OR_PASSWORD_ERROR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends AbstractWebIntegrationTest {

    @Test
    void test_register_new_user_expect_not_null_token_response() throws Exception {
        LoginCreateDto loginCreateDto = new LoginCreateDto("foo", "bar", null);

        ResultActions resultActions = mockMvc.perform(
                        post("/register")
                                .content(Objects.requireNonNull(asJsonString(loginCreateDto)))
                                .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(resultActions).isNotNull();

    }

    @Test
    void test_login_expect_not_null_token_response() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto("testuser", "1234ABV");

        String response = mockMvc.perform(
                        post("/login")
                                .content(Objects.requireNonNull(asJsonString(loginRequestDto)))
                                .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        SuccessfulResult singleObject = asSingleObject(response, SuccessfulResult.class);
        assertThat(singleObject).isNotNull();
        assertThat(singleObject.getToken()).isNotNull();
    }

    @Test
    void test_try_to_login_with_username_not_exists_expect_error_response() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto("foo", "bar");

        String contentAsString = mockMvc.perform(
                        post("/login")
                                .content(Objects.requireNonNull(asJsonString(loginRequestDto)))
                                .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorResponse errorResponse = asSingleObject(contentAsString, ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isEqualTo(WRONG_LOGIN_OR_PASSWORD_ERROR);
    }

    @Test
    void test_try_to_login_with_wrong_password_expect_error_response() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto("testuser", "bar");

        String contentAsString = mockMvc.perform(
                        post("/login")
                                .content(Objects.requireNonNull(asJsonString(loginRequestDto)))
                                .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorResponse errorResponse = asSingleObject(contentAsString, ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isEqualTo(WRONG_LOGIN_OR_PASSWORD_ERROR);
    }
}
