package org.fludland.sso.controller;

import org.fludland.sso.dtos.SuccessfulRegistration;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthNController {
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthNController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCreateDto dto) {
        return ResponseEntity.ok("+");
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessfulRegistration> register(@RequestBody LoginCreateDto loginCreateDto) {

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
}
