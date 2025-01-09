package org.fludland.api.service;

import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.SuccessfulResult;

public interface AuthService {
    SuccessfulResult login(LoginCreateDto loginCreateDto);
}
