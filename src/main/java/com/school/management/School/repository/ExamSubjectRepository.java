package com.school.management.School.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.ExamSubject;

public interface ExamSubjectRepository extends JpaRepository<ExamSubject, Long>{
	boolean existsByExamIdAndSubjectId(Long examId, Long subjectId);
	
	List<ExamSubject> findByExamId(Long examId);

    
}
