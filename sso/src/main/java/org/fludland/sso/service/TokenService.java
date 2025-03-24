package org.fludland.sso.service;

import org.fludland.sso.entities.TokenEntity;
import org.fludland.sso.entities.User;

public interface TokenService {
    TokenEntity save(User user);
    void revoke();
    void refreshToken();
}
