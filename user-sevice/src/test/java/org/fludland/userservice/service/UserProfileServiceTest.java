package org.fludland.userservice.service;

import org.fludland.userservice.exceptions.ProfileNotFoundException;
import org.fludland.userservice.repository.UserProfileRepository;
import org.fludland.userservice.service.impl.UserProfileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserProfileServiceTest {
    private UserProfileRepository mockUserProfileRepository;
    private UserProfileService userProfileService;

    @BeforeEach
    void init() {
        mockUserProfileRepository = mock(UserProfileRepository.class);
        userProfileService = new UserProfileServiceImpl(mockUserProfileRepository);
    }

    @Test
    void test_find_user_by_id_9999_expected_throws_exception() {
        when(mockUserProfileRepository.findByUserId(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProfileNotFoundException.class, () -> userProfileService.getProfileByUserId(9999L));
    }
}
