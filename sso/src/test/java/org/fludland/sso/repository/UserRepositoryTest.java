package org.fludland.sso.repository;

import org.fludland.sso.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SqlGroup(@Sql({"classpath:/data.sql"}))
class UserRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void test() {
        List<User> all = userRepository.findAll();
        assertThat(all).isNotNull();
        assertThat(all).hasSize(2);
        System.out.println("Fetched users: " + all);
    }
}
