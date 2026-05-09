package com.school.management.School.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.AssignFeeRequest;
import com.school.management.School.service.FeeAssignmentService;

@RestController
@RequestMapping("/api/fees")
public class FeeAssignmentController {
	    @Autowired
	    private FeeAssignmentService feeAssignmentService;

	    @PostMapping("/assign-class")
	    public ResponseEntity<?> assignFee(@RequestBody AssignFeeRequest request) {
	        return ResponseEntity.ok(feeAssignmentService.assignFeeToClass(request));
	    }
}
