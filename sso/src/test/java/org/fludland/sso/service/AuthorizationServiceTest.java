package org.fludland.sso.service;

import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.entities.Profile;
import org.fludland.sso.entities.User;
import org.fludland.sso.exceptions.UsernameAlreadyExistsException;
import org.fludland.sso.repository.ProfileRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class AuthorizationServiceTest {
    private UserRepository mockUserRepository;
    private ProfileRepository mockProfileRepository;
    private TokenUtils mockTokenUtils;

    private AuthorizationService authorizationService;

    @BeforeEach
    public void init() {
        mockTokenUtils = mock(TokenUtils.class);
        mockUserRepository = mock(UserRepository.class);
        mockProfileRepository = mock(ProfileRepository.class);
        authorizationService = new AuthorizationServiceImpl(mockUserRepository, mockProfileRepository, mockTokenUtils);
    }

    @Test
    void test_when_passed_user_auth_data_expected_new_user() {
        LoginCreateDto loginCreateDto = new LoginCreateDto("foo", "bar");

        Profile profile = new Profile();

        when(mockTokenUtils.generateJWT(anyString())).thenReturn("12345");
        when(mockUserRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(mockProfileRepository.save(profile)).thenReturn(profile);

        SuccessfulResult register = authorizationService.register(loginCreateDto);
        assertThat(register.getToken()).isNotNull().isEqualTo("12345");
    }

    @Test
    void test_when_username_already_exists_expected_exception() {
        LoginCreateDto loginCreateDto = new LoginCreateDto("foo", "bar");

        User user = new User();
        user.setUsername("foo");
        when(mockUserRepository.findByUsername("foo")).thenReturn(Optional.of(user));

        UsernameAlreadyExistsException assertedThrows = assertThrows(UsernameAlreadyExistsException.class, () -> authorizationService.register(loginCreateDto));
        assertThat(assertedThrows.getMessage()).contains("foo");
    }
}
