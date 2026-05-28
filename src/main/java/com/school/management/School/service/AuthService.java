package com.school.management.School.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.management.School.dto.LoginRequest;
import com.school.management.School.dto.LoginResponse;
import com.school.management.School.dto.SignupRequest;
import com.school.management.School.entity.Role;
import com.school.management.School.entity.School;
import com.school.management.School.entity.User;
import com.school.management.School.repository.SchoolRepository;
import com.school.management.School.repository.UserRepository;
import com.school.management.School.utility.JwtService;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	public String signup(SignupRequest request) {

		// check email exists
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}

		User user = new User();
		user.setFullName(request.getFullName());
		user.setMobile(request.getMobile());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());

		// 🔥 Important Logic
		if (request.getRole() == Role.SCHOOL_ADMIN) {

			if (request.getCity() == null || request.getState() == null || request.getSchoolEmail() == null) {
				throw new RuntimeException("City, State and School Email are required");
			}

			School school = new School();
			school.setSchoolName(request.getSchoolName());
			school.setCity(request.getCity());
			school.setState(request.getState());
			school.setEmail(request.getSchoolEmail());
			school.setSchoolCode(generateSchoolCode(request.getSchoolName(), request.getCity(), school.getState()));

			school = schoolRepository.save(school);
			user.setSchool(school);
		} else if (request.getRole() == Role.TEACHER) {

			throw new RuntimeException("Teacher signup must include schoolId");

		} else if (request.getRole() == Role.SUPER_ADMIN) {

			user.setSchool(null); // no school

		}

		userRepository.save(user);

		return "User registered successfully";
	}

	private String generateSchoolCode(String schoolName, String city,String state) {

		String school = schoolName.replaceAll("[^A-Za-z ]", "").toUpperCase();

		StringBuilder initials = new StringBuilder();

		for (String word : school.split("\\s+")) {

			if (!word.isEmpty()) {
				initials.append(word.charAt(0));
			}
		}

		String cityPart = city.replaceAll("[^A-Za-z]", "").toUpperCase();

		cityPart = cityPart.length() > 3 ? cityPart.substring(0, 3) : cityPart;

		String stateCode = state.replaceAll("[^A-Za-z]", "").toUpperCase();

		stateCode = stateCode.length() > 2 ? stateCode.substring(0, 2) : stateCode;

		int random = 100 + new Random().nextInt(900);

		return initials + cityPart +stateCode+ random;
	}

	public LoginResponse login(LoginRequest request) {

		User user = userRepository.findByMobile(request.getMobile())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

			throw new RuntimeException("Invalid password");

		}

		if (!user.getIsActive()) {

			throw new RuntimeException("User inactive");

		}

		String token = jwtService.generateToken(user.getMobile());
		String refreshToken = jwtService.generateRefreshToken(user.getMobile());

		return new LoginResponse(

				"Login successful",

				user.getFullName(),

				user.getRole().name(),

				user.getId(),

				user.getSchool() != null ? user.getSchool().getId() : null,

				token,

				refreshToken);

	}

}
