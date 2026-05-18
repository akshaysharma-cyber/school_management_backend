package com.school.management.School.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{
	
	 List<Exam> findBySchoolId(Long schoolId);
	 
	 Optional<Exam> findByIdAndSchoolIdAndClassName(
		        Long id,
		        Long schoolId,
		        String className
		);

}
