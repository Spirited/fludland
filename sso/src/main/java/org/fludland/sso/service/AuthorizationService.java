package org.fludland.sso.service;

public interface AuthorizationService {
    String login(String username, String password);
    String register(String username, String password);
    void delete(String username);
    void logout(String username);
}
