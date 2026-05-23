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
        long upcoming =
                examRepository.countUpcoming(
                        schoolId,
                        today
                );

        // Ongoing
        long ongoing =
                examRepository.countOngoing(
                        schoolId,
                        today
                );

        // Students
        long students =
                studentRepository.countBySchoolId(
                        schoolId
                );

        // Average Result
        Double avg =
                resultRepository
                        .getAveragePercentage(
                                schoolId
                        );

        return new ExamDashboardDTO(
                upcoming,
                ongoing,
                students,
                avg == null ? 0 : avg
        );
    }
	
	public List<RecentResultDTO> getRecentResults(
	        Long schoolId
	) {

	    List<Object[]> rows =
	    		resultRepository.getRecentResults(schoolId);

	    List<RecentResultDTO> list =
	            new ArrayList<>();

	    for (Object[] row : rows) {

	        Long examId =
	                ((Number) row[0]).longValue();

	        String topperName =
	        		resultRepository.findTopperName(examId);

	        Double topperPercentage =
	        		resultRepository.findTopperPercentage(examId);

	        list.add(

	            new RecentResultDTO(

	                    (String) row[1],

	                    (String) row[2],

	                    ((Number) row[3]).longValue(),

	                    ((Number) row[4]).doubleValue(),

	                    topperName,

	                    topperPercentage,

	                    row[5] != null
	                            ? row[5].toString()
	                            : "",

	                    true
	            )
	        );
	    }

	    return list;
	}

}
