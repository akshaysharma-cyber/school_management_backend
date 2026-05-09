package com.school.management.School.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.StudentResult;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long>{
	Optional<StudentResult> findByExamIdAndStudentId(Long examId, Long studentId);
}
