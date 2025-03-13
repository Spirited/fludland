package org.fludland.sso.service.impl;

import org.fludland.sso.repository.TokenRepository;
import org.fludland.sso.service.TokenService;
import org.fludland.sso.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final TokenUtils tokenUtils;

    @Autowired
    public TokenServiceImpl(final TokenRepository tokenRepository, final TokenUtils tokenUtils) {
        this.tokenRepository = tokenRepository;
        this.tokenUtils = tokenUtils;
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
