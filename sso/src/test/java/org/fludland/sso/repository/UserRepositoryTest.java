package org.fludland.sso.repository;

import org.fludland.sso.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SqlGroup(@Sql({"classpath:/cleanup.sql", "classpath:/data.sql"}))
class UserRepositoryTest extends AbstractDataIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void test_when_execute_findAll_then_return_2_users() {
        List<User> all = userRepository.findAll();
        assertThat(all).isNotNull();
        assertThat(all).hasSize(2);
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
    @Transactional
    void test_save_users_profile_then_return_not_null_profile() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        User saved = userRepository.save(user);

        assertThat(saved).isNotNull();
        assertThat(saved.getUsername()).isEqualTo("test");
        assertThat(saved.getPassword()).isEqualTo("password");
    }

    @Test
    void test_find_user_by_username_expected_not_null_result() {
        Optional<User> byUsername = userRepository.findByUsername("super-user");

        assertThat(byUsername).isNotNull();
        assertThat(byUsername.isPresent()).isTrue();
    }
}
