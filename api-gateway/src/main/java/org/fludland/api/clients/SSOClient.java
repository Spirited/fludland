package org.fludland.api.clients;

import org.fludland.sso.dtos.AuthorizationDto;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.dtos.UserDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sso", url = "${api.sso.host}")
public interface SSOClient {

    @PostMapping(value = "/login")
    SuccessfulResult login(AuthorizationDto loginCreateDto);

    @PostMapping(value = "/register")
    SuccessfulResult register(LoginCreateDto loginCreateDto);

    @GetMapping(value = "/users")
    UserDetailsDto getUser(@RequestParam(name = "token") String token);

    @GetMapping(value = "/users/{userId}")
    UserDetailsDto getUser(@PathVariable Long userId);
}
