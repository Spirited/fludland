package org.fludland.api.config;

import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "sso", url = "${api.sso.host}")
public interface SSOClient {
    @PostMapping(value = "/login")
    SuccessfulResult login(LoginCreateDto loginCreateDto);
}
