package com.mybank.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.userservice.dto.LoginRequest;
import com.mybank.userservice.dto.UserResponse;
import com.mybank.userservice.model.User;
import com.mybank.userservice.repository.UserRepository;
import com.mybank.userservice.service.UserService;
import com.mybank.userservice.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    	System.out.println("User credentials::"+request.getEmail());
    	System.out.println("User credentials::"+request.getPassword());
       String token = service.login(request);
        return ResponseEntity.ok(Map.of("token", token));
    }
}

