package com.school.management.School.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.StudentMarks;

public interface StudentMarksRepository extends JpaRepository<StudentMarks, Long>{
	Optional<StudentMarks> findBySchoolIdAndExamIdAndStudentIdAndSubjectId(
	        Long schoolId,
	        Long examId,
	        Long studentId,
	        Long subjectId
	    );
	
	List<StudentMarks> findByExamIdAndStudentId(
            Long examId,
            Long studentId
    );

    List<StudentMarks> findByExamId(Long examId);
}
