package com.mybank.userservice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mybank.userservice.dto.LoginRequest;
import com.mybank.userservice.dto.UserResponse;
import com.mybank.userservice.model.User;
import com.mybank.userservice.repository.UserRepository;
import com.mybank.userservice.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;
	
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;
	
	public UserResponse register(User user) {
		 user.setPassword(encoder.encode(user.getPassword()));
		 User savedUser = repository.save(user);
	        
	        UserResponse response = new UserResponse();
	        response.setId(savedUser.getId());
	        response.setUserName(savedUser.getUserName());
	        return response;
	        
	}
	
	public String login(LoginRequest request) {
		 User user = repository.findByEmail(request.getEmail())
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        if (!encoder.matches(request.getPassword(), user.getPassword())) {
	            throw new RuntimeException("Invalid password");
	        }

	        return jwtUtil.generateToken(user.getId().toString());
		
	}

	
}
