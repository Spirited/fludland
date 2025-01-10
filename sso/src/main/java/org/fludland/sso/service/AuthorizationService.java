package org.fludland.sso.service;

import org.fludland.sso.dtos.AuthorizationDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.dtos.LoginCreateDto;

public interface AuthorizationService {
    SuccessfulResult login(AuthorizationDto loginCreateDto);
    SuccessfulResult register(LoginCreateDto login);
    void delete(String username);
    void logout(String username);
}
