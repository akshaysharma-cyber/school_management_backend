package com.school.management.School.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.CreateExamRequest;
import com.school.management.School.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
	@Autowired
    private ExamService examService;

    @PostMapping("/create")
    public ResponseEntity<?> createExam(@RequestBody CreateExamRequest request) {
        return ResponseEntity.ok(examService.createExam(request));
    }

}
