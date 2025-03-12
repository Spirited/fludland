package org.fludland.sso.service.impl;

import org.fludland.sso.repository.TokenRepository;
import org.fludland.sso.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void save(String token) {

    }

    @Override
    public void revoke() {

    }

    @Override
    public void refreshToken() {

    }
}
