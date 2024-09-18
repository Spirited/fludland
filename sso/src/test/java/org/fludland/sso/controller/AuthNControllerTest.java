package org.fludland.sso.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fludland.sso.dtos.LoginCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // don't replace our DB with an in-memory one
@ContextConfiguration(initializers = AuthNControllerTest.DockerPostgresDataSourceInitializer.class)
public class AuthNControllerTest {

    @Container
    private static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
            .withDatabaseName("sso")
            .withUsername("postgres")
            .withPassword("123456");

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void test_register_new_user_expect_not_null_token_response() throws Exception {
        LoginCreateDto loginCreateDto = new LoginCreateDto("foo", "bar");

        ResultActions resultActions = mockMvc.perform(post("/register")
                        .content(Objects.requireNonNull(asJsonString(loginCreateDto))).contentType("application/json"))
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

    public static class DockerPostgresDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            String jdbcUrl = postgresqlContainer.getJdbcUrl();
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + jdbcUrl,
                    "spring.datasource.username=" + postgresqlContainer.getUsername(),
                    "spring.datasource.password=" + postgresqlContainer.getPassword()
            );
        }
    }
}
