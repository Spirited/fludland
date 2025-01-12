package org.fludland.sso.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fludland.sso.AbstractIntegrationTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@SqlGroup(@Sql({"classpath:/cleanup.sql"}))
public abstract class AbstractWebIntegrationTest extends AbstractIntegrationTest {
    final ObjectMapper mapper = new ObjectMapper();

    public <T> T asSingleObject(final String json, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.readValue(json, clazz);
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
