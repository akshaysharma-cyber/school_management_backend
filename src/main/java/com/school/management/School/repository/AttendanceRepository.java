package com.school.management.School.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	Optional<Attendance> findByStudentIdAndAttendanceDate(Long studentId, LocalDate date);

}
