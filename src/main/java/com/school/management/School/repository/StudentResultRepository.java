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
			Double getAveragePercentage(
			    @Param("schoolId") Long schoolId
			);

	@Query(
			value = """
			SELECT
			e.id,
			e.exam_name,
			e.class_name,
			COUNT(r.id),
			COALESCE(AVG(r.percentage),0),
			e.result_publish_date

			FROM student_results r

			INNER JOIN exams e
			ON r.exam_id = e.id

			WHERE e.school_id = :schoolId

			GROUP BY
			e.id,
			e.exam_name,
			e.class_name,
			e.result_publish_date

			ORDER BY e.result_publish_date DESC
			""",
			nativeQuery = true
			)
			List<Object[]> getRecentResults(
			        @Param("schoolId")
			        Long schoolId
			);
			
			@Query(
					value = """
					SELECT *
					FROM student_results
					WHERE exam_id=:examId
					ORDER BY percentage DESC
					LIMIT 1
					""",
					nativeQuery = true
					)
					String findTopperName(
					        @Param("examId")
					        Long examId
					);


			@Query(
					value = """
					SELECT COALESCE(MAX(percentage),0)
					FROM student_results
					WHERE exam_id=:examId
					""",
					nativeQuery = true
					)
					Double findTopperPercentage(
					        @Param("examId")
					        Long examId
					);
}
