package com.school.management.School.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	Optional<Attendance> findByStudentIdAndAttendanceDate(Long studentId, LocalDate date);
	
	long countBySchoolIdAndAttendanceDateAndStatus(
	        Long schoolId,
	        LocalDate attendanceDate,
	        Attendance.Status status
	);
	
	List<Attendance> findBySchoolIdAndClassNameAndAttendanceDate(

	        Long schoolId,

	        String className,

	        LocalDate attendanceDate
	);
	
	boolean existsBySchoolIdAndClassNameAndAttendanceDate(
	        Long schoolId,
	        String className,
	        LocalDate attendanceDate
	);

}
