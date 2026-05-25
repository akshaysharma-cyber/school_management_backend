package com.school.management.School.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.dto.ExamDashboardDTO;
import com.school.management.School.dto.RecentResultDTO;
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

	public List<RecentResultDTO> getRecentResults(Long schoolId) {

		List<Object[]> rows = resultRepository.getRecentResults(schoolId);

		return rows.stream().map(r -> new RecentResultDTO(

				// examName
				r[0] != null ? r[0].toString() : "",

				// className
				r[1] != null ? r[1].toString() : "",

				// students
				r[2] != null ? ((Number) r[2]).longValue() : 0L,

				// average
				r[3] != null ? ((Number) r[3]).doubleValue() : 0.0,

				// topperName
				r[4] != null ? r[4].toString() : "-",

				// topperPercentage
				r[5] != null ? ((Number) r[5]).doubleValue() : 0.0,

				// publishedOn
				r[6] != null ? r[6].toString() : "",

				// smsSent
				r[7] != null && Boolean.parseBoolean(r[7].toString())

		)).toList();
	}

}
