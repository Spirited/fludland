package org.fludland.api.controller;

import org.fludland.api.dto.CreateNewAccount;
import org.fludland.api.service.AuthService;
import org.fludland.sso.dtos.AuthorizationDto;
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
    public ResponseEntity<SuccessfulResult> login(@RequestBody AuthorizationDto loginCreateDto) {
        return ResponseEntity.ok(authService.login(loginCreateDto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SuccessfulResult> register(@RequestBody CreateNewAccount createNewAccount) {
        return ResponseEntity.ok(authService.register(createNewAccount));
    }
}
