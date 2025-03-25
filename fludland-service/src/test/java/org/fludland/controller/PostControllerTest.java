package org.fludland.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.fludland.common.ErrorType;
import org.fludland.common.ErrorResponse;
import org.fludland.service.CreatePostDto;
import org.fludland.service.EditPostDto;
import org.fludland.service.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        String contentAsString = mockMvc.perform(post("/posts")
                        .content(Objects.requireNonNull(asJsonString(createPostDto))).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PostDto singleObject = asSingleObject(contentAsString, PostDto.class);

        assertThat(singleObject).isNotNull();
        assertThat(singleObject.getId()).isNotNull();
        assertThat(singleObject.getTitle()).isNotNull();
        assertThat(singleObject.getTitle()).isEqualTo("test");
        assertThat(singleObject.getContent()).isNotNull();
        assertThat(singleObject.getContent()).isEqualTo("test content");
        assertThat(singleObject.getCreatedAt()).isNotNull();
        assertThat(singleObject.getUpdatedAt()).isNull();
    }

    @Test
    void test_get_all_posts() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        List<PostDto> posts = asSingleObject(contentAsString, new TypeReference<List<PostDto>>() {});

        assertThat(posts).hasSize(8);
    }

    @Test
    void test_get_all_posts_expected_not_found_exception() throws Exception {
        String contentAsString = mockMvc.perform(get("/posts/9999"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorResponse errorResponse = asSingleObject(contentAsString, ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isEqualTo(ErrorType.POST_NOT_FOUND_EXCEPTION);
        assertThat(errorResponse.getErrorCodes().getErrorCode()).isEqualTo(2);
    }

    @Test
    void test_get_post_id_expected_not_null_result() throws Exception {
        String contentAsString = mockMvc.perform(get("/posts/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PostDto post = asSingleObject(contentAsString, PostDto.class);

        assertThat(post).isNotNull();
        assertThat(post.getId()).isNotNull();
        assertThat(post.getTitle()).isNotNull();
        assertThat(post.getTitle()).isEqualTo("test");
        assertThat(post.getContent()).isNotNull();
        assertThat(post.getContent()).isEqualTo("large content");
        assertThat(post.getCreatedAt()).isNotNull();
    }

    @Test
    void test_fetch_post_success() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/posts/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(resultActions).isNotNull();
    }

    @Test
    void test_get_all_user_posts_expected_empty_list() throws Exception {
        String contentAsString = mockMvc.perform(get("/posts/users/{id}", 999999))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<PostDto> postDtos = asSingleObject(contentAsString, new TypeReference<List<PostDto>>() {});
        assertThat(postDtos).isNotNull();
        assertThat(postDtos).isEmpty();
    }

    @Test
    void test_get_all_user_posts_expected_three_posts() throws Exception {
        String contentAsString = mockMvc.perform(get("/posts/users/{id}", 777))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<PostDto> postDtos = asSingleObject(contentAsString, new TypeReference<List<PostDto>>() {});
        assertThat(postDtos).isNotNull();
        assertThat(postDtos).hasSize(3);
    }

    @Test
    void test_update_post_expected_not_null_result() throws Exception {
        EditPostDto editPostDto = createEditPostDto();

        String contentAsString = mockMvc.perform(put("/posts/1")
                .content(Objects.requireNonNull(asJsonString(editPostDto))).contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PostDto postDto = asSingleObject(contentAsString, PostDto.class);

        assertThat(postDto).isNotNull();
        assertThat(postDto.getId()).isNotNull();
        assertThat(postDto.getId()).isEqualTo(1L);
        assertThat(postDto.getTitle()).isNotNull();
        assertThat(postDto.getTitle()).isEqualTo(editPostDto.getTitle());
        assertThat(postDto.getContent()).isNotNull();
        assertThat(postDto.getContent()).isEqualTo(editPostDto.getContent());
        assertThat(postDto.getCreatedAt()).isNotNull();
        assertThat(postDto.getUpdatedAt()).isNotNull();
    }

    @Test
    void test_delete_post_expected_not_found_exception() throws Exception {
        String contentAsString = mockMvc.perform(delete("/posts/9999"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorResponse errorResponse = asSingleObject(contentAsString, ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isEqualTo(ErrorType.POST_NOT_FOUND_EXCEPTION);
        assertThat(errorResponse.getErrorCodes().getErrorCode()).isEqualTo(2);
    }

    @Test
    void test_delete_post_expected_success_result() throws Exception {
        String contentAsString = mockMvc.perform(delete("/posts/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorResponse errorResponse = asSingleObject(contentAsString, ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isEqualTo(ErrorType.SUCCESS_ERROR_CODE);

        String contentAsString2 = mockMvc.perform(delete("/posts/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorResponse errorResponse2 = asSingleObject(contentAsString2, ErrorResponse.class);
        assertThat(errorResponse2).isNotNull();
        assertThat(errorResponse2.getErrorCodes()).isNotNull();
        assertThat(errorResponse2.getErrorCodes()).isEqualTo(ErrorType.POST_NOT_FOUND_EXCEPTION);
        assertThat(errorResponse2.getErrorCodes().getErrorCode()).isEqualTo(2);
    }

    @Test
    void test_put_post_thumbs_expected_exception() throws Exception {
        String contentAsString = mockMvc.perform(post("/posts/99999/users/1/thumbs"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorResponse errorResponse = asSingleObject(contentAsString, ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isEqualTo(ErrorType.POST_NOT_FOUND_EXCEPTION);
        assertThat(errorResponse.getErrorCodes().getErrorCode()).isEqualTo(2);
    }

    @Test
    void test_get_total_thumbs_if_post_doesnt_exists_expected_exception() throws Exception {
        String contentAsString = mockMvc.perform(get("/posts/99999/thumbs/total"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ErrorResponse errorResponse = asSingleObject(contentAsString, ErrorResponse.class);
        assertThat(errorResponse).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isNotNull();
        assertThat(errorResponse.getErrorCodes()).isEqualTo(ErrorType.POST_NOT_FOUND_EXCEPTION);
        assertThat(errorResponse.getErrorCodes().getErrorCode()).isEqualTo(2);
    }

    @Test
    void test_put_post_thumbs_expected_two_likes() throws Exception {
        mockMvc.perform(post("/posts/1/users/1/thumbs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        mockMvc.perform(post("/posts/1/users/2/thumbs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String contentAsString3 = mockMvc.perform(get("/posts/1/thumbs/total"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(contentAsString3).isNotNull();
        assertThat(contentAsString3).isEqualTo("2");
    }

    @Test
    void test_put_post_user_thumbs_and_revert_it_expected_zero_likes() throws Exception {
        mockMvc.perform(post("/posts/1/users/1/thumbs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String contentAsString = mockMvc.perform(get("/posts/1/thumbs/total"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(contentAsString).isNotNull();
        assertThat(contentAsString).isEqualTo("1");

        mockMvc.perform(post("/posts/1/users/1/thumbs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String contentAsString3 = mockMvc.perform(get("/posts/1/thumbs/total"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(contentAsString3).isNotNull();
        assertThat(contentAsString3).isEqualTo("0");
    }


    public static EditPostDto createEditPostDto() {
        return new EditPostDto("edited_title", "added new test content");
    }
}
