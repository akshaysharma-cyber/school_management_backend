package com.school.management.School.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.service.SubjectService;

@RestController
@RequestMapping("/api")
public class SubjectController {
	
	@Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects-by-class")
    public ResponseEntity<?> getSubjects(
            @RequestParam Long schoolId,
            @RequestParam String className) {

        return ResponseEntity.ok(
                subjectService.getSubjectsByClass(schoolId, className)
        );
    }

}
