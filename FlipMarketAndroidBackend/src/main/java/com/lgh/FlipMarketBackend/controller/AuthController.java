package com.lgh.FlipMarketBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgh.FlipMarketBackend.dto.LoginRequest;
import com.lgh.FlipMarketBackend.dto.LoginResponse;
import com.lgh.FlipMarketBackend.service.AuthService;


@RestController
@RequestMapping("/api/android/")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		String token = authService.login(request.getUsername(), request.getPassword());
		return ResponseEntity.ok(new LoginResponse(token));
	}

}
