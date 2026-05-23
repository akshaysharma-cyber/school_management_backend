package com.school.management.School.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.CreateExamRequest;
import com.school.management.School.service.ExamDashboardService;
import com.school.management.School.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
	@Autowired
    private ExamService examService;
	@Autowired
	private ExamDashboardService dashboardService;

    @PostMapping("/create")
    public ResponseEntity<?> createExam(@RequestBody CreateExamRequest request) {
        return ResponseEntity.ok(examService.createExam(request));
    }
    
    @GetMapping("/All-Exam")
    public ResponseEntity<?> getAllExams(
            @RequestParam Long schoolId
    ) {

        return ResponseEntity.ok(
                examService.getAllExamsBySchool(schoolId)
        );
    }
    
    @GetMapping("/{examId}/subjects")
    public ResponseEntity<?> getExamSubjects(
    		 @PathVariable Long examId,
            @RequestParam Long schoolId,
            @RequestParam String className
    ) {

        return ResponseEntity.ok(
                examService.getExamSubjects(examId,schoolId, className)
        );
    }
    
    
    @GetMapping("/dashboard-stat")
    public ResponseEntity<?> getDashboard(
            @RequestParam Long schoolId
    ) {

        return ResponseEntity.ok(
                dashboardService.getDashboard(schoolId)
        );
    }
    
	@GetMapping("/recent-results")
	public ResponseEntity<?> recentResults(@RequestParam Long schoolId) {

		return ResponseEntity.ok(dashboardService.getRecentResults(schoolId));

	}
}
