package com.school.management.School.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.LoginRequest;
import com.school.management.School.dto.RefreshRequest;
import com.school.management.School.dto.SignupRequest;
import com.school.management.School.service.AuthService;
import com.school.management.School.utility.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
    private JwtService jwtService;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest request) {

		System.out.println("controller hitting------------------");

		String message = authService.signup(request);

		Map<String, String> response = new HashMap<>();
		response.put("message", message);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(

			@RequestBody RefreshRequest req

	) {

		String mobile = null;
		try {
			mobile = jwtService.extractUser(req.getRefreshToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String access = null;
		try {
			access = jwtService.generateToken(mobile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, String> map = new HashMap<>();

		map.put("accessToken", access);

		return ResponseEntity.ok(map);

	}
}
