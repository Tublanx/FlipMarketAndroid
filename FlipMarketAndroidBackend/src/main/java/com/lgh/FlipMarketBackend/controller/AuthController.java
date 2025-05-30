package com.lgh.FlipMarketBackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgh.FlipMarketBackend.dto.EmailCheckRequest;
import com.lgh.FlipMarketBackend.dto.LoginRequest;
import com.lgh.FlipMarketBackend.dto.LoginResponse;
import com.lgh.FlipMarketBackend.dto.RegisterRequest;
import com.lgh.FlipMarketBackend.dto.RegisterResponse;
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
	
	@PostMapping("/check-email")
	public ResponseEntity<?> checkEmail(@RequestBody EmailCheckRequest request) {
		boolean isExists = authService.checkEmail(request.getUsername());
		Map<String, Boolean> response = new HashMap<>();
		response.put("isExists", isExists);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		try {
			authService.register(request);
			return ResponseEntity.ok(new RegisterResponse(true, "회원가입 성공"));
		} catch (Exception e) {
			return ResponseEntity.ok(new RegisterResponse(false, "회원가입 실패"));
		}
	}

}
