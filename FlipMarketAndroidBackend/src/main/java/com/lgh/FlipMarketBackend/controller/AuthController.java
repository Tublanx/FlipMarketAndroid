package com.lgh.FlipMarketBackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgh.FlipMarketBackend.dto.EmailCheckRequest;
import com.lgh.FlipMarketBackend.dto.EmailCheckResponse;
import com.lgh.FlipMarketBackend.dto.LoginRequest;
import com.lgh.FlipMarketBackend.dto.LoginResponse;
import com.lgh.FlipMarketBackend.dto.RegisterRequest;
import com.lgh.FlipMarketBackend.dto.RegisterResponse;
import com.lgh.FlipMarketBackend.service.AuthService;

@RestController
@RequestMapping("/api/android/")
public class AuthController {

	private final AuthService authService;

	private final BCryptPasswordEncoder passwordEncoder;

	public AuthController(AuthService authService, BCryptPasswordEncoder passwordEncoder) {
		this.authService = authService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		String token = authService.login(request.getUsername(), request.getPassword());
		return ResponseEntity.ok(new LoginResponse(token, authService.findByEmail(request.getUsername())));
	}

	@PostMapping("/check-email")
	public ResponseEntity<?> checkEmail(@RequestBody EmailCheckRequest request) {
		boolean exists = authService.checkEmail(request.getUsername());
		return ResponseEntity.ok(new EmailCheckResponse(exists));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		// 비밀번호 암호화 로직
		String inputPassword = request.getPassword();
		String encodedPassword = passwordEncoder.encode(inputPassword);

		request.setPassword(encodedPassword);

		try {
			authService.register(request);
			return ResponseEntity.ok(new RegisterResponse(true, "회원가입 성공"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new RegisterResponse(false, "회원가입 실패"));
		}
	}

}
