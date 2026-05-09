package com.school.management.School.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.ClassSubject;

public interface ClassSubjectRepository extends JpaRepository<ClassSubject, Long> {
	List<ClassSubject> findBySchoolIdAndClassName(Long schoolId, String className);
	
	boolean existsBySchoolIdAndClassNameAndSubject_Id(
	        Long schoolId,
	        String className,
	        Long subjectId
	    );

}
