package org.fludland.api.controller;

import org.fludland.api.service.AuthService;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessfulResult> login(@RequestBody LoginCreateDto loginCreateDto) {
        return ResponseEntity.ok(authService.login(loginCreateDto));
    }
}
