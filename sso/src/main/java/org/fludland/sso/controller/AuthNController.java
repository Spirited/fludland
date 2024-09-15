package org.fludland.sso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthNController {
    @PostMapping("/login")
    public ResponseEntity<String> login(String username, String password) {
        //TODO
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(String username, String password) {
        //TODO
        return ResponseEntity.ok("Register successful");
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
