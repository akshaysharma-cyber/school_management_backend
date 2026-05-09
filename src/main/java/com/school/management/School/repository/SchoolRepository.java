package com.school.management.School.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

}
