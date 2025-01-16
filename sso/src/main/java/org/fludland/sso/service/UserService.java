package org.fludland.sso.service;

import org.fludland.sso.dtos.UserDetailsDto;

public interface UserService {
    UserDetailsDto getUserDetails(String token);
    UserDetailsDto getUserDetails(Long userId);
}
