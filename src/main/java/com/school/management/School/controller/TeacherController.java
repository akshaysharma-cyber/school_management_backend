package com.school.management.School.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.CreateTeacherRequest;
import com.school.management.School.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	@Autowired
    private TeacherService teacherService;

	@PostMapping("/add")
	public ResponseEntity<?> addTeacher(@RequestBody CreateTeacherRequest request) {

	    String message = teacherService.createTeacher(request);

	    Map<String, Object> response = new HashMap<>();
	    response.put("message", message);

	    return ResponseEntity.ok(response);
	}
}
