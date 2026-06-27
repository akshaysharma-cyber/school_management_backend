package com.school.management.School.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.TeacherDetails;



public interface TeacherRepository extends JpaRepository<TeacherDetails, Long> {
	 Optional<TeacherDetails> findByUserId(Long userId);

	    Optional<TeacherDetails> findByEmployeeId(String employeeId);

	    List<TeacherDetails> findBySchoolId(Long schoolId);
	    
	    long countBySchoolId(Long schoolId);
	    
	    Optional<TeacherDetails> findByIdAndSchoolId(Long id, Long schoolId);
}
