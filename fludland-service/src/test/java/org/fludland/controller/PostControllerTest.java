package org.fludland.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fludland.service.CreatePostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerTest extends AbstractWebIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void test_create_new_post_success() throws Exception {
        CreatePostDto createPostDto = new CreatePostDto("test", "test content", 1);
        ResultActions resultActions = mockMvc.perform(post("/posts")
                        .content(Objects.requireNonNull(asJsonString(createPostDto))).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(resultActions).isNotNull();
    }

    @Test
    void test_fetch_post_success() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/posts/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(resultActions).isNotNull();
    }

    public static String asJsonString(final Object obj) {
        String jsonContent;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
