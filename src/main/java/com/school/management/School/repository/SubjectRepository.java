package com.school.management.School.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
