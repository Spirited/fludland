package org.fludland.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ImageControllerTest extends AbstractWebIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void test_add_new_image_expected_image_url_success_result() throws Exception {
        String filePath = "images/starwars.png";
        URL resource = getClass().getClassLoader().getResource(filePath);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + filePath);
        }
        File file = new File(resource.toURI());
        byte[] bytes = Files.readAllBytes(file.toPath());

        MockMultipartFile upladFile
                = new MockMultipartFile(
                "image",
                "starwars.png",
                MediaType.IMAGE_PNG_VALUE,
                bytes
        );

        String contentAsString = mockMvc.perform(multipart("/images")
                        .file(upladFile)
                        .header("Content-Type", "multipart/form-data"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(contentAsString).isNotBlank();
        assertThat(contentAsString).isEqualTo("/images/1");
    }

    @Test
    void test_get_image_by_id_expected_image_success_result() throws Exception {
        String filePath = "images/starwars.png";
        URL resource = getClass().getClassLoader().getResource(filePath);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + filePath);
        }
        File file = new File(resource.toURI());
        byte[] bytes = Files.readAllBytes(file.toPath());

        MockMultipartFile upladFile
                = new MockMultipartFile(
                "image",
                "starwars.png",
                MediaType.IMAGE_PNG_VALUE,
                bytes
        );

        String imageURL = mockMvc.perform(multipart("/images")
                        .file(upladFile)
                        .header("Content-Type", "multipart/form-data"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        byte[] image = mockMvc.perform(get(imageURL))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        assertThat(image).isNotNull();
        assertThat(image.length).isEqualTo(bytes.length);
    }
}
