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

	public ReportCardDTO getReportCard(Long studentId) {

	    Student student = studentRepo.findById(studentId)
	            .orElseThrow(() -> new RuntimeException("Student not found"));

	    List<Object[]> rows = resultRepo.getReport(studentId);

	    List<SubjectResultDTO> subjects = new ArrayList<>();

	    double totalMarks = 0;
	    double obtainedMarks = 0;

	    for (Object[] r : rows) {

	        String subject = r[0] != null ? String.valueOf(r[0]) : "-";

	        double total = r[1] != null ? ((Number) r[1]).doubleValue() : 0;

	        double obtained = r[2] != null ? ((Number) r[2]).doubleValue() : 0;

	        double percentage = total > 0 ? (obtained * 100) / total : 0;

	        String grade;
	        if (percentage >= 91) grade = "A+";
	        else if (percentage >= 81) grade = "A";
	        else if (percentage >= 71) grade = "B";
	        else if (percentage >= 61) grade = "C";
	        else grade = "D";

	        subjects.add(new SubjectResultDTO(
	                subject,
	                total,
	                obtained,
	                percentage,   // keep as Double (no Math.round)
	                grade
	        ));

	        totalMarks += total;
	        obtainedMarks += obtained;
	    }

	    StudentResult finalResult = resultRepo.findByStudentId(studentId)
	            .orElse(null);

	    ReportCardDTO dto = new ReportCardDTO();

	    dto.setStudentName(student.getFullName());
	    dto.setClassName(student.getClassName());
	    dto.setRollNumber(student.getId());

	    dto.setSubjects(subjects);

	    // Overall totals (recommended correction)
	    dto.setTotal(totalMarks);
	    dto.setObtained(obtainedMarks);

	    dto.setPercentage(
	            finalResult != null ? finalResult.getPercentage() : 0.0
	    );

	    dto.setGrade(
	            finalResult != null ? finalResult.getGrade() : "-"
	    );

	    dto.setRank(1);

	    return dto;
	}
}
