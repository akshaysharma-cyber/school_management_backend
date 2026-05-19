package com.school.management.School.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.management.School.dto.StudentRequest;
import com.school.management.School.entity.Student;
import com.school.management.School.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addStudent(

			@RequestPart("student") String studentJson,

			@RequestPart(value = "studentPhoto", required = false) MultipartFile studentPhoto,

			@RequestPart(value = "birthCertificate", required = false) MultipartFile birthCertificate

	) {

		Map<String, String> response = new HashMap<>();

		try {

			ObjectMapper mapper = new ObjectMapper();

			StudentRequest request = mapper.readValue(studentJson, StudentRequest.class);

			String message = studentService.addStudent(request, studentPhoto, birthCertificate);

			response.put("message", message);

			return ResponseEntity.ok(response);

		} catch (RuntimeException ex) {

			response.put("message", ex.getMessage());

			return ResponseEntity.badRequest().body(response);

		} catch (Exception ex) {

			ex.printStackTrace();

			response.put("message", "Upload failed");

			return ResponseEntity.internalServerError().body(response);
		}
	}

	@GetMapping("/by-class/{schoolId}/{className}")
	public List<Student> getStudentsByClass(@PathVariable Long schoolId, @PathVariable String className) {
		return studentService.getStudentsByClass(schoolId, className);
	}

	@GetMapping("/class/{className}")
	public ResponseEntity<?> getStudentsByClass(@PathVariable String className, @RequestParam Long schoolId) {

		return ResponseEntity.ok(studentService.getStudentsByClassForMarks(schoolId, className));
	}

	// ==================================
	// GET ALL STUDENTS
	// ==================================
	@GetMapping("/all")
	public List<Student> getAllStudents(@RequestParam Long schoolId) {

		return studentService.getAllStudents(schoolId);
	}

	// ==================================
	// GET STUDENT BY ID
	// ==================================
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {

		return studentService.getStudentById(id);
	}

	// ==================================
	// UPDATE STUDENT
	// ==================================
	@PutMapping("/update/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {

		return studentService.updateStudent(id, student);
	}

	// ==================================
	// DELETE STUDENT
	// ==================================
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {

		return studentService.deleteStudent(id);
	}

}
