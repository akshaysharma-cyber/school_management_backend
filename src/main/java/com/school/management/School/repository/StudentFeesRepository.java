package com.school.management.School.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.StudentFees;

public interface StudentFeesRepository extends JpaRepository<StudentFees, Long> {
	 // 🔹 Used in assign API (avoid duplicate)
    Optional<StudentFees> findByStudentIdAndAcademicYear(
            Long studentId,
            String academicYear
    );

    // 🔹 Fetch all students of class (dashboard/report)
    List<StudentFees> findByClassNameAndSectionAndAcademicYear(
            String className,
            String section,
            String academicYear
    );

    // 🔹 Get all pending students
    List<StudentFees> findByStatusAndAcademicYear(
            StudentFees.Status status,
            String academicYear
    );
}
