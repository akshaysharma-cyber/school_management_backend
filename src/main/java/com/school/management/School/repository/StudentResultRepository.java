package com.school.management.School.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.management.School.dto.RecentResultDTO;
import com.school.management.School.entity.StudentResult;

public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
	Optional<StudentResult> findByExamIdAndStudentId(Long examId, Long studentId);

	@Query("""
			SELECT COALESCE(AVG(r.percentage),0)
			FROM StudentResult r
			WHERE r.schoolId = :schoolId
			""")
	Double getAveragePercentage(@Param("schoolId") Long schoolId);

	@Query(value = """
			SELECT
			e.exam_name,
			e.class_name,
			COUNT(r.id),
			COALESCE(AVG(r.percentage),0),

			(
			SELECT s.full_name
			FROM student_results sr
			JOIN students s
			ON s.id = sr.student_id
			WHERE sr.exam_id = e.id
			ORDER BY sr.percentage DESC
			LIMIT 1
			),

			(
			SELECT MAX(sr2.percentage)
			FROM student_results sr2
			WHERE sr2.exam_id = e.id
			),

			e.result_publish_date,
			true

			FROM exams e

			LEFT JOIN student_results r
			ON r.exam_id=e.id

			WHERE e.school_id=:schoolId

			GROUP BY
			e.id,
			e.exam_name,
			e.class_name,
			e.result_publish_date

			ORDER BY e.result_publish_date DESC
			LIMIT 5
			""", nativeQuery = true)
	List<Object[]> getRecentResults(@Param("schoolId") Long schoolId);

	@Query(value = """
			SELECT *
			FROM student_results
			WHERE exam_id=:examId
			ORDER BY percentage DESC
			LIMIT 1
			""", nativeQuery = true)
	String findTopperName(@Param("examId") Long examId);

	@Query(value = """
			SELECT COALESCE(MAX(percentage),0)
			FROM student_results
			WHERE exam_id=:examId
			""", nativeQuery = true)
	Double findTopperPercentage(@Param("examId") Long examId);

	@Query(value = """

			SELECT

			sub.subject_name,

			sm.max_marks,

			sm.marks_obtained,

			ROUND(
			(sm.marks_obtained*100)
			/ sm.max_marks,
			2
			)

			FROM student_marks sm

			JOIN subjects sub
			ON sub.id = sm.subject_id

			WHERE sm.student_id = :studentId

			ORDER BY sub.subject_name

			""", nativeQuery = true)

	List<Object[]> getReport(@Param("studentId") Long studentId);
	
	Optional<StudentResult> findByStudentId(Long studentId);
}
