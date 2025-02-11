package org.fludland.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fludland.userservice.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@SqlGroup(@Sql({"classpath:/cleanup.sql", "classpath:/data.sql"}))
public abstract class AbstractWebIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    final ObjectMapper mapper;

    public AbstractWebIntegrationTest() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public <T> T asSingleObject(final String json, Class<T> clazz) {

        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T asSingleObject(final String json, final TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String asJsonString(final Object obj) {
        String jsonContent;
        try {
            jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
