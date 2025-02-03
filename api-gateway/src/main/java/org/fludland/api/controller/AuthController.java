package org.fludland.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.fludland.api.dto.CreateNewAccount;
import org.fludland.api.service.AuthService;
import org.fludland.sso.dtos.LoginRequestDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginCreateDto, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie
                .from("token", authService.login(loginCreateDto).getToken())
                .path("/")
                .secure(true)
                .httpOnly(true)
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SuccessfulResult> register(@RequestBody CreateNewAccount createNewAccount) {
        return ResponseEntity.ok(authService.register(createNewAccount));
    }
}
