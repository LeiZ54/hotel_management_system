package org.lei.hotel_management_system.controller;

import jakarta.validation.Valid;
import org.lei.hotel_management_system.DTO.UserLoginDTO;
import org.lei.hotel_management_system.DTO.UserRegisterDTO;
import org.lei.hotel_management_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO loginUser) {
        return ResponseEntity.ok(authService.login(loginUser));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegisterDTO registerUser) {
        return ResponseEntity.ok(authService.register(registerUser));
    }
}
