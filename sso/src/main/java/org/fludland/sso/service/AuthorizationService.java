package org.fludland.sso.service;

import org.fludland.sso.dtos.LoginRequestDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.dtos.LoginCreateDto;

public interface AuthorizationService {
    SuccessfulResult login(LoginRequestDto loginCreateDto);
    SuccessfulResult register(LoginCreateDto login);
    void delete(String username);
    void logout(String username);
    void changePassword(String username);
    void verifyEmail(String username);
    void rememberMe(String username);
}
