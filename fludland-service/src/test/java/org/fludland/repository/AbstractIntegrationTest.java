package org.fludland.repository;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)// don't replace our DB with an in-memory one
@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
@ContextConfiguration(initializers = AbstractIntegrationTest.DockerPostgresDataSourceInitializer.class)
public abstract class AbstractIntegrationTest {
    @Container
    private static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
            .withDatabaseName("fludland")
            .withUsername("postgres")
            .withPassword("123456");

    @Autowired
    SpringLiquibase liquibase;

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
