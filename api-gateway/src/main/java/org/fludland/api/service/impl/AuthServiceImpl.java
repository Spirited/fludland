package org.fludland.api.service.impl;

import org.fludland.api.clients.SSOClient;
import org.fludland.api.clients.UserServiceClient;
import org.fludland.api.dto.CreateNewAccount;
import org.fludland.api.service.AuthService;
import org.fludland.sso.dtos.LoginRequestDto;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.userservcie.CreateProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.fludland.utils.JWTUtils.extractUserIdFromToken;

@Service
public class AuthServiceImpl implements AuthService {
    private final SSOClient ssoClient;
    private final UserServiceClient userService;

    @Autowired
    public AuthServiceImpl(
            final SSOClient ssoClient,
            final UserServiceClient userService
    ) {
        this.ssoClient = ssoClient;
        this.userService = userService;
    }

    @Override
    public SuccessfulResult login(LoginRequestDto loginCreateDto) {
        return ssoClient.login(loginCreateDto);
    }

    @Override
    public SuccessfulResult register(CreateNewAccount createNewAccount) {
        LoginCreateDto loginCreateDto = new LoginCreateDto(
                createNewAccount.getUsername(),
                createNewAccount.getPassword(),
                createNewAccount.getEmail()
        );

        SuccessfulResult register = ssoClient.register(loginCreateDto);

        Long userId = extractUserIdFromToken(register.getToken());
        CreateProfileDto profileDto = new CreateProfileDto(
                userId,
                createNewAccount.getFirstName(),
                createNewAccount.getLastName(),
                createNewAccount.getDateOfBirth(),
                createNewAccount.getGender(),
                createNewAccount.getPhoneNumber(),
                createNewAccount.getEmail(),
                null
        );

        userService.createProfile(profileDto);

        return register;
    }
}
