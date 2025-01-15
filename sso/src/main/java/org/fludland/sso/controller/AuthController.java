package org.fludland.sso.controller;

import org.fludland.sso.dtos.AuthorizationDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessfulResult> login(@RequestBody AuthorizationDto dto) {
        return ResponseEntity.ok(authorizationService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessfulResult> register(@RequestBody LoginCreateDto loginCreateDto) {

        return ResponseEntity.ok(authorizationService.register(loginCreateDto));
    }

    @PostMapping("/logout")
    public void logout(String username) {
        //TODO
    }

    @DeleteMapping("/delete")
    public void deleteAccount(String username) {
        //TODO
    }

    //TODO: do verify profile, forget password, change password, remember Me
}
