package com.school.management.School.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.StudentRequest;
import com.school.management.School.entity.Student;
import com.school.management.School.repository.StudentRepository;
import com.school.management.School.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
    private StudentService studentService;
	

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.addStudent(request));
    }
    
    

}
