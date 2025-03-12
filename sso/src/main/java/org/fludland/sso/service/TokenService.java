package org.fludland.sso.service;

public interface TokenService {
    void save(String token);
    void revoke();
    void refreshToken();
}
