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
			    e.exam_type,
			    e.class_name,
			    COUNT(DISTINCT r.student_id),
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

			    e.result_publish_date

			FROM exams e

			LEFT JOIN student_results r
			    ON r.exam_id = e.id

			WHERE e.school_id = :schoolId
			  AND e.academic_year = :academicYear
			  AND e.result_publish_date = (

			        SELECT MAX(result_publish_date)
			        FROM exams
			        WHERE school_id = :schoolId
			          AND academic_year = :academicYear

			  )

			GROUP BY
			    e.id,
			    e.exam_type,
			    e.class_name,
			    e.result_publish_date

			ORDER BY e.class_name
					""", nativeQuery = true)
	List<Object[]> getRecentResults(@Param("schoolId") Long schoolId, @Param("academicYear") String academicYear);

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
			    s.subject_name,
			    e.exam_type,
			    es.max_marks,
			    sm.marks_obtained

			FROM student_marks sm

			JOIN subjects s
			    ON s.id = sm.subject_id

			JOIN exams e
			    ON e.id = sm.exam_id

			JOIN exam_subjects es
			    ON es.exam_id = sm.exam_id
			    AND es.subject_id = sm.subject_id

			WHERE sm.school_id = :schoolId
			AND sm.student_id = :studentId
			AND e.academic_year = :academicYear
			AND e.class_name = :className

			ORDER BY s.subject_name,e.id

			""", nativeQuery = true)
	List<Object[]> getConsolidatedReport(Long schoolId, Long studentId, String academicYear, String className);

	Optional<StudentResult> findByStudentId(Long studentId);
}
