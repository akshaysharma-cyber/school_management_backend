package com.school.management.School.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Optional<Student> findByAdmissionNumber(String admissionNumber);
	 List<Student> findByClassName(String className);	 
	 boolean existsByIdAndSchoolIdAndClassName(
			    Long id,
			    Long schoolId,
			    String className
			   
			);
	 List<Student> findBySchoolIdAndClassName(
	            Long schoolId,
	            String className
	    );
	 
	 long countBySchoolId(Long schoolId);
	 List<Student> findBySchoolId(Long schoolId);
	 Optional<Student> findBySchoolIdAndId(
		        Long schoolId,
		        Long id
		);	 
}
