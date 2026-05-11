package com.school.management.School.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.StudentRequest;
import com.school.management.School.entity.Student;
import com.school.management.School.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public ResponseEntity<?> addStudent(@RequestBody StudentRequest request) {

		Map<String, String> response = new HashMap<>();

		try {

			String message = studentService.addStudent(request);

			response.put("message", message);

			return ResponseEntity.ok(response);

		} catch (RuntimeException ex) {

			response.put("message", ex.getMessage());

			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/by-class/{schoolId}/{className}")
	public List<Student> getStudentsByClass(
	        @PathVariable Long schoolId,
	        @PathVariable String className
	) {
	    return studentService.getStudentsByClass(
	            schoolId,
	            className
	    );
	}

}
