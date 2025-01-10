package org.fludland.api.service.impl;

import org.fludland.api.config.SSOClient;
import org.fludland.api.service.AuthService;
import org.fludland.sso.dtos.AuthorizationDto;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final SSOClient ssoClient;

    @Autowired
    public AuthServiceImpl(SSOClient ssoClient) {
        this.ssoClient = ssoClient;
    }

    @Override
    public SuccessfulResult login(AuthorizationDto loginCreateDto) {
        return ssoClient.login(loginCreateDto);
    }

    @Override
    public SuccessfulResult register(LoginCreateDto loginCreateDto) {
        return ssoClient.register(loginCreateDto);
    }
}
