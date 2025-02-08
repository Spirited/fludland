package org.fludland.sso.service;

import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.LoginRequestDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.entities.User;
import org.fludland.sso.exceptions.UsernameAlreadyExistsException;
import org.fludland.sso.exceptions.WrongLoginOrPasswordException;
import org.fludland.sso.repository.UserRepository;
import org.fludland.sso.service.impl.AuthorizationServiceImpl;
import org.fludland.sso.utils.TokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AuthorizationServiceTest {
    private UserRepository mockUserRepository;
    private TokenUtils mockTokenUtils;

    private AuthorizationService authorizationService;

    @BeforeEach
    public void init() {
        mockTokenUtils = mock(TokenUtils.class);
        mockUserRepository = mock(UserRepository.class);
        authorizationService = new AuthorizationServiceImpl(
                mockUserRepository,
                mockTokenUtils);
    }

    @Test
    void test_when_passed_user_auth_data_expected_new_user_and_token() {
        LoginCreateDto loginCreateDto = new LoginCreateDto("foo", "bar", null);

        User saved = new User();
        saved.setUsername(loginCreateDto.getUsername());
        saved.setPassword(loginCreateDto.getPassword());
        saved.setId(1L);

        when(mockUserRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(mockUserRepository.save(any())).thenReturn(saved);
        when(mockTokenUtils.generateJWT(anyLong(), anyString())).thenReturn("12345");

        SuccessfulResult register = authorizationService.register(loginCreateDto);
        assertThat(register.getToken()).isNotNull().isEqualTo("12345");
    }

    @Test
    void test_when_username_already_exists_expected_exception() {
        LoginCreateDto loginCreateDto = new LoginCreateDto("foo", "bar", null);

        User user = new User();
        user.setUsername("foo");
        when(mockUserRepository.findByUsername("foo")).thenReturn(Optional.of(user));

        UsernameAlreadyExistsException assertedThrows = assertThrows(
                UsernameAlreadyExistsException.class,
                () -> authorizationService.register(loginCreateDto)
        );
        assertThat(assertedThrows.getMessage()).contains("foo");
    }

    @Test
    void test_login_user_expected_successful_result() {
        LoginRequestDto loginCreateDto = new LoginRequestDto("foo", "bar");
        User user = new User();
        user.setId(100L);
        user.setUsername("foo");
        user.setPassword("bar");

        when(mockUserRepository.findByUsername("foo")).thenReturn(Optional.of(user));
        when(mockTokenUtils.generateJWT(anyLong(), anyString())).thenReturn("12345");

        SuccessfulResult result = authorizationService.login(loginCreateDto);
        assertThat(result.getToken()).isNotNull();
    }

    @Test
    void test_login_with_wrong_password_expected_exception() {
        LoginRequestDto loginCreateDto = new LoginRequestDto("foo", "bar");
        User user = new User();
        user.setId(100L);
        user.setUsername("foo");
        user.setPassword("mypassword");

        when(mockUserRepository.findByUsername("foo")).thenReturn(Optional.of(user));

        assertThrows(WrongLoginOrPasswordException.class, () -> authorizationService.login(loginCreateDto));
    }
}
