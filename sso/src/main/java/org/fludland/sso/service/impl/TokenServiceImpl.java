package org.fludland.sso.service.impl;

import org.fludland.sso.entities.TokenEntity;
import org.fludland.sso.entities.User;
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
    public TokenEntity save(User user) {
        String token = tokenUtils.generateJWT(user.getId(), user.getUsername());
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);

        return tokenRepository.save(tokenEntity);
    }

    @Override
    public void revoke() {

    }

    @Override
    public void refreshToken() {

    }
}
