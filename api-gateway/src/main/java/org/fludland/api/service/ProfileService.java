package org.fludland.api.service;

import org.fludland.api.dto.ProfileDto;

public interface ProfileService {
    ProfileDto getMyProfile(Long userId);
    ProfileDto getUserProfile(String token, Long id);
}
