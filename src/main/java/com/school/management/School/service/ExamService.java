package com.school.management.School.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.school.management.School.dto.CreateExamRequest;
import com.school.management.School.dto.SubjectDto;
import com.school.management.School.dto.SubjectMarksDto;
import com.school.management.School.entity.Exam;
import com.school.management.School.entity.ExamSubject;
import com.school.management.School.entity.Subject;
import com.school.management.School.repository.ClassSubjectRepository;
import com.school.management.School.repository.ExamRepository;
import com.school.management.School.repository.SubjectRepository;

@Service
@Transactional
public class ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private ClassSubjectRepository classSubjectRepository;
	@Autowired
	private SubjectRepository subjectRepository;

	public String createExam(CreateExamRequest request) {

		// ✅ 1. Basic validation
		if (request.getSubjects() == null || request.getSubjects().isEmpty()) {
			throw new RuntimeException("At least one subject required");
		}

		if (examRepository.existsBySchoolIdAndClassNameAndExamTypeAndAcademicYear(request.getSchoolId(),
				request.getClassName(), request.getExamType(), request.getAcademicYear())) {

			throw new RuntimeException("Exam already exists for this class and academic year");
		}
		
		// 2. Parse Dates
	    LocalDate startDate =
	            LocalDate.parse(request.getStartDate());

	    LocalDate endDate =
	            LocalDate.parse(request.getEndDate());

	    // 3. Duplicate Exam Validation
	    if (examRepository
	            .existsBySchoolIdAndClassNameAndExamTypeAndAcademicYear(
	                    request.getSchoolId(),
	                    request.getClassName(),
	                    request.getExamType(),
	                    request.getAcademicYear())) {

	        throw new RuntimeException(
	                "Exam Type already exists for this class and academic year");
	    }

	    // 4. Date Overlap Validation  <-- KEEP HERE
	    long overlap =
	            examRepository.countOverlappingExams(
	                    request.getSchoolId(),
	                    request.getClassName(),
	                    request.getAcademicYear(),
	                    startDate,
	                    endDate);

	    if (overlap > 0) {

	        throw new RuntimeException(
	                "Exam dates overlap with existing exam");
	    }

	    // 5. Publish Date Validation
	    LocalDate publishDate =
	            LocalDate.parse(request.getResultPublishDate());

	    if (publishDate.isBefore(endDate)) {

	        throw new RuntimeException(
	                "Publish date must be after exam end date");
	    }

		// ✅ 2. Create Exam
		Exam exam = new Exam();
		exam.setSchoolId(request.getSchoolId());
		exam.setExamName(request.getExamName());
		exam.setExamType(request.getExamType());
		exam.setClassName(request.getClassName());
		exam.setAcademicYear(request.getAcademicYear());	
		exam.setStartDate(LocalDate.parse(request.getStartDate()));
		exam.setEndDate(LocalDate.parse(request.getEndDate()));
		exam.setDescription(request.getDescription());
		exam.setPassingPercentage(request.getPassingPercentage());
		exam.setGradingSystem(request.getGradingSystem());
		exam.setResultPublishDate(LocalDate.parse(request.getResultPublishDate()));


		// ✅ 3. Subjects + Total Marks
		List<ExamSubject> subjectList = new ArrayList<>();
		int total = 0;

		Set<Long> subjectIds = new HashSet<>();

		for (SubjectDto s : request.getSubjects()) {

		    // Duplicate Subject Validation
		    if (!subjectIds.add(s.getSubjectId())) {
		        throw new RuntimeException(
		                "Duplicate subject selected");
		    }

		    // Max Marks Validation
		    if (s.getMaxMarks() == null
		            || s.getMaxMarks() <= 0) {

		        throw new RuntimeException(
		                "Max Marks must be greater than 0");
		    }

		    // Subject-Class Mapping Validation
		    boolean allowed =
		            classSubjectRepository
		                    .existsBySchoolIdAndClassNameAndSubject_Id(
		                            request.getSchoolId(),
		                            request.getClassName(),
		                            s.getSubjectId());

		    if (!allowed) {
		        throw new RuntimeException(
		                "Subject not allowed for this class");
		    }

		    ExamSubject es = new ExamSubject();
		    es.setSchoolId(request.getSchoolId());
		    es.setSubjectId(s.getSubjectId());
		    es.setMaxMarks(s.getMaxMarks());
		    es.setExam(exam);

		    total += s.getMaxMarks();
		    subjectList.add(es);
		}

		exam.setTotalMarks(total);
		exam.setSubjects(subjectList);

		// ✅ 4. Save
		examRepository.save(exam);

		return "Exam created successfully";
	}

	public List<Exam> getAllExamsBySchool(Long schoolId) {

		return examRepository.findBySchoolId(schoolId);
	}

	public List<SubjectMarksDto> getExamSubjects(
	        Long schoolId,
	        String academicYear,
	        String examType,
	        String className) {

	    Exam exam = examRepository
	            .findBySchoolIdAndAcademicYearAndExamTypeAndClassName(
	                    schoolId,
	                    academicYear,
	                    examType,
	                    className)
	            .orElseThrow(() ->
	            new RuntimeException(
                        "No exam setup found for selected Academic Year, Exam Type and Class."
                ));

	    List<SubjectMarksDto> result = new ArrayList<>();

	    for (ExamSubject es : exam.getSubjects()) {

	        Subject subject = subjectRepository
	                .findById(es.getSubjectId())
	                .orElse(null);

	        if (subject != null) {

	            SubjectMarksDto dto = new SubjectMarksDto();
	            dto.setExamId(exam.getId());
	            dto.setTotalMarks(exam.getTotalMarks());
	            dto.setSubjectId(subject.getId());
	            dto.setName(subject.getSubjectName());
	            dto.setMarks(es.getMaxMarks());

	            result.add(dto);
	        }
	    }

	    return result;
	}
	
	public List<String> getExamTypes(Long schoolId, String academicYear) {

	    return examRepository
	            .findBySchoolIdAndAcademicYear(schoolId, academicYear)
	            .stream()
	            .map(Exam::getExamType)
	            .distinct()
	            .toList();
	}

}
