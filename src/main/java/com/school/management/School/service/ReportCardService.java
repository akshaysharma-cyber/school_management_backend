package com.school.management.School.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.dto.ExamWiseMarksDTO;
import com.school.management.School.dto.ReportCardDTO;
import com.school.management.School.dto.SubjectResultDTO;
import com.school.management.School.entity.School;
import com.school.management.School.entity.Student;
import com.school.management.School.entity.StudentResult;
import com.school.management.School.repository.SchoolRepository;
import com.school.management.School.repository.SchoolRepository;
import com.school.management.School.repository.StudentRepository;
import com.school.management.School.repository.StudentResultRepository;

@Service
public class ReportCardService {
	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private StudentResultRepository resultRepo;
	
	@Autowired
	private SchoolRepository schoolRepository;

	 public ReportCardDTO getReportCard(
	            Long schoolId,
	            Long studentId,
	            String academicYear,
	            String className) {

	        Student student =
	                studentRepo.findById(studentId)
	                        .orElseThrow(
	                                () -> new RuntimeException("Student not found")
	                        );

	        List<Object[]> rows =
	                resultRepo.getConsolidatedReport(
	                        schoolId,
	                        studentId,
	                        academicYear,
	                        className
	                );

	        Map<String, SubjectResultDTO> subjectMap =
	                new LinkedHashMap<>();

	        double grandTotal = 0;
	        double grandObtained = 0;

	        for (Object[] row : rows) {

	            String subjectName =
	                    String.valueOf(row[0]);

	            String examType  =
	                    String.valueOf(row[1]);

	            Double maxMarks =
	                    row[2] == null
	                            ? 0.0
	                            : ((Number) row[2]).doubleValue();

	            Double obtained =
	                    row[3] == null
	                            ? 0.0
	                            : ((Number) row[3]).doubleValue();

	            SubjectResultDTO subjectDto =
	                    subjectMap.computeIfAbsent(
	                            subjectName,
	                            k -> {

	                                SubjectResultDTO dto =
	                                        new SubjectResultDTO();

	                                dto.setSubject(subjectName);
	                                dto.setExams(new ArrayList<>());
	                                dto.setTotalObtained(0.0);
	                                dto.setTotalMax(0.0);

	                                return dto;
	                            });

	            subjectDto.getExams().add(
	                    new ExamWiseMarksDTO(
	                    		examType ,
	                            obtained,
	                            maxMarks
	                    )
	            );

	            subjectDto.setTotalObtained(
	                    subjectDto.getTotalObtained()
	                            + obtained
	            );

	            subjectDto.setTotalMax(
	                    subjectDto.getTotalMax()
	                            + maxMarks
	            );
	        }

	        List<SubjectResultDTO> subjects =
	                new ArrayList<>(subjectMap.values());

	        for (SubjectResultDTO dto : subjects) {

	            grandObtained += dto.getTotalObtained();

	            grandTotal += dto.getTotalMax();

	            double percent =
	                    dto.getTotalMax() > 0
	                            ? (dto.getTotalObtained() * 100.0)
	                            / dto.getTotalMax()
	                            : 0;

	            dto.setPercentage(
	                    Math.round(percent * 100.0) / 100.0
	            );

	            dto.setGrade(
	                    getGrade(percent)
	            );
	        }

	        double overallPercentage =
	                grandTotal > 0
	                        ? (grandObtained * 100.0)
	                        / grandTotal
	                        : 0;
	                
	                School school =
	                	    schoolRepository.findById(schoolId)
	                	        .orElseThrow(() ->
	                	            new RuntimeException("School not found"));

	        ReportCardDTO report =
	                new ReportCardDTO();

	        report.setStudentName(
	                student.getFullName()
	        );
	        
	        report.setSchoolName(
	        	    school.getSchoolName()
	        	);
	        report.setDistrict(school.getCity()); // or district column if you have one
	        report.setState(school.getState());
	        report.setAcademicYear(academicYear);

	        report.setClassName(
	                student.getClassName()
	        );

	        report.setRollNumber(
	                student.getId()
	        );

	        report.setSubjects(
	                subjects
	        );

	        report.setTotal(
	                grandTotal
	        );

	        report.setObtained(
	                grandObtained
	        );

	        report.setPercentage(
	                Math.round(overallPercentage * 100.0)
	                        / 100.0
	        );

	        report.setGrade(
	                getGrade(overallPercentage)
	        );

	        report.setRank(1);

	        return report;
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
