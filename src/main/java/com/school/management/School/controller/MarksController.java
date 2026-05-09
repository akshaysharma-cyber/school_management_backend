package com.school.management.School.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.MarksEntryRequest;
import com.school.management.School.service.MarksService;

@RestController
@RequestMapping("/api/marks")
public class MarksController {
	
	@Autowired
    private MarksService marksService;

    @PostMapping("/save")
    public ResponseEntity<?> saveMarks(@RequestBody MarksEntryRequest request) {
        return ResponseEntity.ok(marksService.saveMarks(request));
    }

}
