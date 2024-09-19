package org.fludland.sso.service;

import org.fludland.sso.dtos.SuccessfulRegistration;
import org.fludland.sso.dtos.LoginCreateDto;

public interface AuthorizationService {
    String login(String username, String password);
    SuccessfulRegistration register(LoginCreateDto login);
    void delete(String username);
    void logout(String username);
}
