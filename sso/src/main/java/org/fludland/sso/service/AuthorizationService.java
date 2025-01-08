package org.fludland.sso.service;

import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.dtos.LoginCreateDto;

public interface AuthorizationService {
    SuccessfulResult login(LoginCreateDto loginCreateDto);
    SuccessfulResult register(LoginCreateDto login);
    void delete(String username);
    void logout(String username);
}
