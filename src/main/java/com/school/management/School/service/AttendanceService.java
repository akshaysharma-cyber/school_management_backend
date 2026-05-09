package com.school.management.School.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.dto.AttendanceRequest;
import com.school.management.School.entity.Attendance;
import com.school.management.School.entity.Student;
import com.school.management.School.repository.AttendanceRepository;
import com.school.management.School.repository.StudentRepository;

@Service
public class AttendanceService {
	
	 @Autowired
	    private AttendanceRepository attendanceRepository;

	    @Autowired
	    private StudentRepository studentRepository;

	    public String markAttendance(AttendanceRequest request) {

	        LocalDate date = LocalDate.parse(request.getDate());

	        // 🔹 Step 1: Get all students of class
	        List<Student> students = studentRepository
	                .findBySchoolIdAndClassNameAndSection(
	                        request.getSchoolId(),
	                        request.getClassName(),
	                        request.getSection()
	                );

	        // 🔹 Step 2: Convert marked list to map
	        Map<Long, String> markedMap = new HashMap<>();

	        if (request.getMarkedStudents() != null) {
	            for (AttendanceRequest.StudentAttendance s : request.getMarkedStudents()) {
	                markedMap.put(s.getStudentId(), s.getStatus());
	            }
	        }

	        // 🔹 Step 3: Loop all students
	        for (Student student : students) {

	            Attendance.Status status;

	            if (markedMap.containsKey(student.getId())) {
	                status = Attendance.Status.valueOf(markedMap.get(student.getId()));
	            } else {
	                status = Attendance.Status.PRESENT; // ✅ default
	            }

	            // 🔹 Step 4: Insert or Update
	            Attendance existing = attendanceRepository
	                    .findByStudentIdAndAttendanceDate(student.getId(), date)
	                    .orElse(null);

	            if (existing != null) {
	                existing.setStatus(status);
	                attendanceRepository.save(existing);
	            } else {
	                Attendance attendance = new Attendance();
	                attendance.setSchoolId(request.getSchoolId());
	                attendance.setStudentId(student.getId());
	                attendance.setClassName(request.getClassName());
	                attendance.setSection(request.getSection());
	                attendance.setAttendanceDate(date);
	                attendance.setStatus(status);

	                attendanceRepository.save(attendance);
	            }
	        }

	        return "Attendance saved successfully";
	    }
}
