package com.school.management.School.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Optional<Student> findByAdmissionNumber(String admissionNumber);
	
	List<Student> findBySchoolIdAndClassNameAndSection(
	        Long schoolId, String className, String section
	    );
	
	 List<Student> findByClassNameAndSection(String className, String section);
	 
	 boolean existsByIdAndSchoolIdAndClassNameAndSection(
			    Long id,
			    Long schoolId,
			    String className,
			    String section
			);
	 
	 List<Student> findBySchoolIdAndClassName(
	            Long schoolId,
	            String className
	    );

}
