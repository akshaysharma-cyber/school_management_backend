package com.school.management.School.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{

}
