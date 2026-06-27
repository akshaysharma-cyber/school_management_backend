package com.school.management.School.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.CreateTeacherRequest;
import com.school.management.School.dto.TeacherResponse;
import com.school.management.School.entity.TeacherDetails;
import com.school.management.School.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	@Autowired
    private TeacherService teacherService;

	@PostMapping("/add")
	public ResponseEntity<?> addTeacher(@RequestBody CreateTeacherRequest request) {

		try {

			String message = teacherService.createTeacher(request);

			Map<String, Object> response = new HashMap<>();

			response.put("message", message);

			return ResponseEntity.ok(response);

		} catch (RuntimeException e) {

			Map<String, Object> error = new HashMap<>();

			error.put("message", e.getMessage());

			return ResponseEntity.badRequest().body(error);
		}
	}
	
	// GET ALL TEACHERS
	@GetMapping("/all")
	public List<TeacherResponse> getAllTeachers(
	        @RequestParam Long schoolId
	) {
	    return teacherService.getAllTeachers(
	            schoolId
	    );
	}

    // GET TEACHER BY ID
    @GetMapping("/{id}")
    public TeacherDetails getTeacherById(
            @PathVariable Long id
    ) {
        return teacherService.getTeacherById(id);
    }

    // UPDATE TEACHER
    @PutMapping("/update/{id}")
    public TeacherDetails updateTeacher(
            @PathVariable Long id,
            @RequestBody TeacherDetails teacher
    ) {
        return teacherService.updateTeacher(id, teacher);
    }

    // DELETE TEACHER
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(
            @PathVariable Long id,
            @RequestParam Long schoolId
    ) {
        String message = teacherService.deleteTeacher(id, schoolId);
        return ResponseEntity.ok(message);
    }
}
