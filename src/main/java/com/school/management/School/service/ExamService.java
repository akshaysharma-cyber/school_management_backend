package com.school.management.School.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.School.dto.CreateExamRequest;
import com.school.management.School.dto.SubjectDto;
import com.school.management.School.entity.Exam;
import com.school.management.School.entity.ExamSubject;
import com.school.management.School.repository.ClassSubjectRepository;
import com.school.management.School.repository.ExamRepository;

@Service
@Transactional
public class ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private ClassSubjectRepository classSubjectRepository;

	public String createExam(CreateExamRequest request) {

		// ✅ 1. Basic validation
		if (request.getSubjects() == null || request.getSubjects().isEmpty()) {
			throw new RuntimeException("At least one subject required");
		}

		// ✅ 2. Create Exam
		Exam exam = new Exam();

		exam.setSchoolId(request.getSchoolId());
		exam.setExamName(request.getExamName());
		exam.setExamType(request.getExamType());

		exam.setClassName(request.getClassName());
		exam.setSection(request.getSection());

		exam.setAcademicYear(request.getAcademicYear());

		exam.setStartDate(LocalDate.parse(request.getStartDate()));
		exam.setEndDate(LocalDate.parse(request.getEndDate()));

		exam.setDescription(request.getDescription());

		exam.setPassingPercentage(request.getPassingPercentage());
		exam.setGradingSystem(request.getGradingSystem());

		exam.setResultPublishDate(LocalDate.parse(request.getResultPublishDate()));

		exam.setAllowReexam(request.getAllowReexam());
		exam.setAllowMarksEntry(request.getAllowMarksEntry());
		exam.setSendSms(request.getSendSms());

		// ✅ 3. Subjects + Total Marks
		List<ExamSubject> subjectList = new ArrayList<>();
		int total = 0;

		for (SubjectDto s : request.getSubjects()) {

			// 🔴 VALIDATION (VERY IMPORTANT)
			boolean allowed = classSubjectRepository.existsBySchoolIdAndClassNameAndSubject_Id(request.getSchoolId(),
					request.getClassName(), s.getSubjectId());

			if (!allowed) {
				throw new RuntimeException("Subject not allowed for this class");
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

}
