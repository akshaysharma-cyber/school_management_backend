package com.school.management.School.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.management.School.dto.FeeStructureRequest;
import com.school.management.School.service.FeeStructureService;

@RestController
@RequestMapping("/api/fees")
public class FeeStructureController {
	
	@Autowired
    private FeeStructureService feeStructureService;

    @PostMapping("/structure")
    public ResponseEntity<?> create(@RequestBody FeeStructureRequest request) {
        return ResponseEntity.ok(feeStructureService.createFeeStructure(request));
    }

}
