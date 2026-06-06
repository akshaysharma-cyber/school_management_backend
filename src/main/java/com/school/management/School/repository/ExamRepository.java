package com.school.management.School.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.management.School.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

	List<Exam> findBySchoolId(Long schoolId);

	Optional<Exam> findByIdAndSchoolIdAndClassName(Long id, Long schoolId, String className);

	@Query("""
			    SELECT COUNT(e)
			    FROM Exam e
			    WHERE e.schoolId=:schoolId
			    AND e.startDate > :today
			""")
	long countUpcoming(Long schoolId, LocalDate today);

	@Query("""
			    SELECT COUNT(e)
			    FROM Exam e
			    WHERE e.schoolId=:schoolId
			    AND :today BETWEEN
			    e.startDate
			    AND e.endDate
			""")
	long countOngoing(Long schoolId, LocalDate today);

	boolean existsBySchoolIdAndClassNameAndExamTypeAndAcademicYear(Long schoolId, String className, String examType,
			String academicYear);

	@Query("""
			SELECT COUNT(e)
			FROM Exam e
			WHERE e.schoolId = :schoolId
			AND e.className = :className
			AND e.academicYear = :academicYear
			AND (
			    :startDate <= e.endDate
			    AND :endDate >= e.startDate
			)
			""")
	long countOverlappingExams(Long schoolId, String className, String academicYear, LocalDate startDate,
			LocalDate endDate);
	
	Optional<Exam> findBySchoolIdAndAcademicYearAndExamTypeAndClassName(
	        Long schoolId,
	        String academicYear,
	        String examType,
	        String className
	);
	
	List<Exam> findBySchoolIdAndAcademicYear(
	        Long schoolId,
	        String academicYear
	);

}
