package com.school.management.School.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
	
	
	/*
	 * List<Subject> findBySchoolId(Long schoolId);
	 * 
	 * List<Subject> findBySchoolIdAndClassName(Long schoolId, String className);
	 */
	 
}
