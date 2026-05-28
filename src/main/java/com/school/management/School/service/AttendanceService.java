package com.school.management.School.service;

import java.time.LocalDate;
import java.util.ArrayList;
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

		if ("TEACHER".equalsIgnoreCase(request.getRole())) {

			if (!date.equals(LocalDate.now())) {

				throw new RuntimeException("Teacher can edit attendance only for today");
			}
		}

		// 🔹 Step 1: Get all students of class
		List<Student> students = studentRepository.findBySchoolIdAndClassName(request.getSchoolId(),
				request.getClassName());

		// =========================================
		// ABSENT STUDENTS MAP
		// =========================================

		Map<Long, String> markedMap = new HashMap<>();

		if (request.getMarkedStudents() != null) {

			for (AttendanceRequest.StudentAttendance s : request.getMarkedStudents()) {

				markedMap.put(s.getStudentId(), s.getStatus());
			}
		}

		boolean updated = false;

		// =========================================
		// SAVE ATTENDANCE
		// =========================================

		for (Student student : students) {

			Attendance.Status status;

			// If teacher marked absent
			if (markedMap.containsKey(student.getId())) {

				status = Attendance.Status.valueOf(markedMap.get(student.getId()));

			} else {

				// Default Present
				status = Attendance.Status.PRESENT;
			}

			Attendance existing = attendanceRepository.findByStudentIdAndAttendanceDate(student.getId(), date)
					.orElse(null);

			// =========================
			// UPDATE EXISTING
			// =========================

			if (existing != null) {

				existing.setStatus(status);

				attendanceRepository.save(existing);

				updated = true;

			} else {

				// =========================
				// INSERT NEW
				// =========================

				Attendance attendance = new Attendance();

				attendance.setSchoolId(request.getSchoolId());

				attendance.setStudentId(student.getId());

				attendance.setClassName(request.getClassName());

				attendance.setAttendanceDate(date);

				attendance.setStatus(status);

				attendanceRepository.save(attendance);
			}
		}

		return updated ? "Attendance updated successfully" : "Attendance marked successfully";
	}

	public List<Map<String, Object>> getAttendanceByDate(

			Long schoolId, String className, String date

	) {

		LocalDate attendanceDate = LocalDate.parse(date);

		List<Attendance> attendanceList = attendanceRepository.findBySchoolIdAndClassNameAndAttendanceDate(schoolId,
				className, attendanceDate);

		List<Map<String, Object>> response = new ArrayList<>();

		for (Attendance a : attendanceList) {

			Map<String, Object> map = new HashMap<>();

			map.put("studentId", a.getStudentId());

			map.put("status", a.getStatus());

			response.add(map);
		}

		return response;
	}
}
