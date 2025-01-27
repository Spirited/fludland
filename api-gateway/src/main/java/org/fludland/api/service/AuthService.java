package org.fludland.api.service;

import org.fludland.api.dto.CreateNewAccount;
import org.fludland.sso.dtos.LoginRequestDto;
import org.fludland.sso.dtos.SuccessfulResult;

public interface AuthService {
    SuccessfulResult login(LoginRequestDto loginCreateDto);
    SuccessfulResult register(CreateNewAccount loginCreateDto);
}
