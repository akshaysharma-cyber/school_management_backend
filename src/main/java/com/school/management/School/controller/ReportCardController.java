package com.school.management.School.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.ReportCardDTO;
import com.school.management.School.service.ReportCardService;

@RestController
@RequestMapping("/api/report-card")
public class ReportCardController {

	@Autowired
	private ReportCardService service;

	@GetMapping("/student")
	public ReportCardDTO getReportCard(

	        @RequestParam Long schoolId,

	        @RequestParam Long studentId,

	        @RequestParam String academicYear,

	        @RequestParam String className

	) {

	    return service.getReportCard(
	            schoolId,
	            studentId,
	            academicYear,
	            className
	    );
	}
}
