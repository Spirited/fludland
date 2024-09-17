package org.fludland.sso.repository;

import org.fludland.sso.entities.Profile;
import org.fludland.sso.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SqlGroup(@Sql({"classpath:/cleanup.sql", "classpath:/data.sql"}))
class UserRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void test_when_execute_findAll_then_return_2_users() {
        List<User> all = userRepository.findAll();
        assertThat(all).isNotNull();
        assertThat(all).hasSize(2);
        System.out.println("Fetched users: " + all);
    }

    @Test
    void test_when_save_new_user_then_return_3_members() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        userRepository.save(user);

        List<User> all = userRepository.findAll();
        assertThat(all).isNotNull();
        assertThat(all).hasSize(3);
    }

    @Test
    void test_select_user_by_id_then_return_not_null_user() {
        User user = userRepository.findById(1L).orElse(null);
        assertThat(user).isNotNull();
    }

    @Test
    void test_just_saved_user_fetch_id_by_id_then_return_user_fields() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        userRepository.save(user);
        User savedUser = userRepository.findById(3L).orElse(null);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("test");
        assertThat(savedUser.getPassword()).isEqualTo("password");

    }

    @Test
    void test_save_users_profile_then_return_not_null_profile() {
        Profile profile = new Profile();

        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.set
        userRepository.save(user);
    }
}
