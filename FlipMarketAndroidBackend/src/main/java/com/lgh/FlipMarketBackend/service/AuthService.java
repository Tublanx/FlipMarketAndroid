package com.lgh.FlipMarketBackend.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lgh.FlipMarketBackend.config.JwtService;
import com.lgh.FlipMarketBackend.dto.RegisterRequest;
import com.lgh.FlipMarketBackend.entity.User;
import com.lgh.FlipMarketBackend.repository.UserRepository;

@Service
public class AuthService {

	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public AuthService(JwtService jwtService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.jwtService = jwtService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public String login(String username, String password) {
		User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("사용자 없음"));

		if (!passwordEncoder.matches(password, user.getPwd())) {
			throw new BadCredentialsException("비밀번호 불일치");
		}

		return jwtService.generatedToken(username);
	}
	
	public boolean checkEmail(String username) {
		return userRepository.countByEmail(username) > 0 ? true : false;
	}

	public void register(RegisterRequest request) {
		User user = new User(request.getUsername(), request.getPassword(), request.getName(), request.getAge(),
				request.getPhoneNum(), request.getRole());
		
		userRepository.save(user);
	}

}
