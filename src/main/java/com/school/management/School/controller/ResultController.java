package com.school.management.School.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.service.ResultService;

@RestController
@RequestMapping("/api/results")
public class ResultController {

	@Autowired
	private ResultService resultService;

	@GetMapping
	public ResponseEntity<?> getResult(@RequestParam Long schoolId, @RequestParam Long examId,
			@RequestParam String className, @RequestParam String section) {

		return ResponseEntity.ok(resultService.getResult(schoolId, examId, className, section));
	}

}
