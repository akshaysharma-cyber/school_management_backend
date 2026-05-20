package com.school.management.School.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.AttendanceRequest;
import com.school.management.School.entity.Student;
import com.school.management.School.repository.StudentRepository;
import com.school.management.School.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private StudentRepository repository;

	@GetMapping("/students")
	public List<Student> getStudents(@RequestParam Long schoolId, @RequestParam String className,
			@RequestParam String section) {

		System.out.println("schoolId = " + schoolId);
		System.out.println("className = " + className);
		System.out.println("section = " + section);

		return repository.findBySchoolIdAndClassNameAndSection(schoolId, className, section);
	}

	@PostMapping("/mark")
	public ResponseEntity<?> markAttendance(@RequestBody AttendanceRequest request) {

		attendanceService.markAttendance(request);

		Map<String, String> response = new HashMap<>();
		response.put("message", "Attendance marked successfully");

		return ResponseEntity.ok(response);
	}

	@GetMapping("/by-date")
	public ResponseEntity<?> getAttendanceByDate(

			@RequestParam Long schoolId,

			@RequestParam String className,

			@RequestParam String section,

			@RequestParam String date

	) {

		return ResponseEntity.ok(

				attendanceService.getAttendanceByDate(schoolId, className, section, date));
	}

}
