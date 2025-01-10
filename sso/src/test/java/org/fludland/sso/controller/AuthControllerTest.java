package org.fludland.sso.controller;

import org.fludland.sso.dtos.LoginCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends AbstractWebIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void test_register_new_user_expect_not_null_token_response() throws Exception {
        LoginCreateDto loginCreateDto = new LoginCreateDto("foo", "bar", null, null, null);

        ResultActions resultActions = mockMvc.perform(post("/register")
                        .content(Objects.requireNonNull(asJsonString(loginCreateDto))).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(resultActions).isNotNull();

    }
}
