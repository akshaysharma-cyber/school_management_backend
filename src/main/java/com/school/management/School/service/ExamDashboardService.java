package com.school.management.School.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.dto.ExamDashboardDTO;
import com.school.management.School.dto.RecentResultDTO;
import com.school.management.School.dto.ResultResponse;
import com.school.management.School.dto.StudentResultDto;
import com.school.management.School.entity.Exam;
import com.school.management.School.repository.ExamRepository;
import com.school.management.School.repository.StudentRepository;
import com.school.management.School.repository.StudentResultRepository;

@Service
public class ExamDashboardService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentResultRepository resultRepository;
	
	@Autowired
	private ResultService resultService;
	


	public ExamDashboardDTO getDashboard(Long schoolId) {

		LocalDate today = LocalDate.now();

		// Upcoming
		long upcoming = examRepository.countUpcoming(schoolId, today);

		// Ongoing
		long ongoing = examRepository.countOngoing(schoolId, today);

		// Students
		long students = studentRepository.countBySchoolId(schoolId);

		// Average Result
		Double avg = resultRepository.getAveragePercentage(schoolId);

		return new ExamDashboardDTO(upcoming, ongoing, students, avg == null ? 0 : avg);
	}

	public List<RecentResultDTO> getRecentResults(
	        Long schoolId,
	        String academicYear) {

	    List<Exam> latestExams =
	            examRepository.findLatestPublishedExams(
	                    schoolId,
	                    academicYear);

	    List<RecentResultDTO> results = new ArrayList<>();

	    for (Exam exam : latestExams) {

	        ResultResponse response = resultService.getResult(
	                schoolId,
	                academicYear,
	                exam.getExamType(),
	                exam.getClassName());

	        StudentResultDto topper =
	                response.getStudents()
	                        .stream()
	                        .max(Comparator.comparing(
	                                StudentResultDto::getPercentage))
	                        .orElse(null);

	        RecentResultDTO dto = new RecentResultDTO();

	        dto.setExamType(exam.getExamType());

	        dto.setClassName(exam.getClassName());

	        dto.setStudents(
	                (long) response.getStudents().size());

	        dto.setAverage(
	                response.getSummary().getAverage());

	        dto.setTopperName(
	                topper != null
	                        ? topper.getName()
	                        : "-");

	        dto.setTopperPercentage(
	                topper != null
	                        ? topper.getPercentage()
	                        : 0.0);

	        dto.setPublishedOn(
	                exam.getResultPublishDate() != null
	                        ? exam.getResultPublishDate().toString()
	                        : "");

	        results.add(dto);
	    }

	    return results;
	}

	

}
