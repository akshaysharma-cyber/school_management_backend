package com.school.management.School.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.management.School.dto.ReportCardDTO;
import com.school.management.School.dto.SubjectResultDTO;
import com.school.management.School.entity.Student;
import com.school.management.School.entity.StudentResult;
import com.school.management.School.repository.StudentRepository;
import com.school.management.School.repository.StudentResultRepository;

@Service
public class ReportCardService {
	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private StudentResultRepository resultRepo;

	public ReportCardDTO getReportCard(Long schoolId,
	        Long studentId,
	        String academicYear,
	        String className) {

		Student student = studentRepo
		        .findBySchoolIdAndId(schoolId, studentId)
		        .orElseThrow(() ->
		                new RuntimeException("Student not found"));
		List<Object[]> rows = resultRepo.getConsolidatedReport(studentId, academicYear, className);

		List<SubjectResultDTO> subjects = new ArrayList<>();

		double totalMarks = 0;
		double obtainedMarks = 0;

		for (Object[] r : rows) {

			String subject = r[0] != null ? String.valueOf(r[0]) : "-";

			double total = r[1] != null ? ((Number) r[1]).doubleValue() : 0;

			double obtained = r[2] != null ? ((Number) r[2]).doubleValue() : 0;

			double percentage = total > 0 ? (obtained * 100) / total : 0;

			subjects.add(new SubjectResultDTO(subject, total, obtained, percentage, getGrade(percentage)));

			totalMarks += total;
			obtainedMarks += obtained;
		}

		double overallPercentage = totalMarks > 0 ? (obtainedMarks * 100) / totalMarks : 0;

		ReportCardDTO dto = new ReportCardDTO();

		dto.setStudentName(student.getFullName());

		dto.setClassName(className);

		dto.setRollNumber(student.getId());

		dto.setSubjects(subjects);

		dto.setTotal(totalMarks);

		dto.setObtained(obtainedMarks);

		dto.setPercentage(overallPercentage);

		dto.setGrade(getGrade(overallPercentage));

		// Later you can calculate actual class rank
		dto.setRank(1);

		return dto;
	}

	private String getGrade(double percentage) {

		if (percentage >= 91)
			return "A+";

		if (percentage >= 81)
			return "A";

		if (percentage >= 71)
			return "B";

		if (percentage >= 61)
			return "C";

		if (percentage >= 33)
			return "D";

		return "F";
	}
}
