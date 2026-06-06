package com.school.management.School.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.School.dto.ExamSubjectDto;
import com.school.management.School.dto.MarksEntryRequest;
import com.school.management.School.dto.StudentMarkResponse;
import com.school.management.School.entity.Exam;
import com.school.management.School.entity.ExamSubject;
import com.school.management.School.entity.StudentMarks;
import com.school.management.School.entity.Subject;
import com.school.management.School.repository.ExamRepository;
import com.school.management.School.repository.ExamSubjectRepository;
import com.school.management.School.repository.StudentMarksRepository;
import com.school.management.School.repository.StudentRepository;
import com.school.management.School.repository.SubjectRepository;

@Service
@Transactional
public class MarksService {

	@Autowired
	private StudentMarksRepository marksRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private ExamSubjectRepository examSubjectRepository;
	@Autowired
	private SubjectRepository SubjectRepository;

	public String saveMarks(MarksEntryRequest request) {

		// ✅ 1. Validate exam
		Exam exam = examRepository.findById(request.getExamId())
				.orElseThrow(() -> new RuntimeException("Exam not found"));

		if (!exam.getSchoolId().equals(request.getSchoolId()) || !exam.getClassName().equals(request.getClassName())) {

			throw new RuntimeException("Invalid exam mapping");
		}

		// ✅ 2. Validate subject
		if (!examSubjectRepository.existsByExamIdAndSubjectId(request.getExamId(), request.getSubjectId())) {

			throw new RuntimeException("Subject not part of exam");
		}

		// ✅ 3. Save marks
		for (MarksEntryRequest.StudentMarksDto s : request.getMarks()) {

			// ✅ student validation
			if (!studentRepository.existsByIdAndSchoolIdAndClassName(s.getStudentId(), request.getSchoolId(),
					request.getClassName())) {

				throw new RuntimeException("Invalid student: " + s.getStudentId());
			}

			StudentMarks entity = marksRepository.findBySchoolIdAndExamIdAndStudentIdAndSubjectId(request.getSchoolId(),
					request.getExamId(), s.getStudentId(), request.getSubjectId()).orElse(new StudentMarks());

			entity.setSchoolId(request.getSchoolId());
			entity.setExamId(request.getExamId());
			entity.setStudentId(s.getStudentId());
			entity.setSubjectId(request.getSubjectId());
			entity.setMarksObtained(s.getMarksObtained());
			entity.setClassName(request.getClassName());

			marksRepository.save(entity);
		}

		return "Marks saved successfully";
	}

	public List<ExamSubjectDto> getSubjects(Long schoolId, Long examId, String className) {

		Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Exam not found"));

		if (!exam.getSchoolId().equals(schoolId) || !exam.getClassName().equals(className)) {

			throw new RuntimeException("Invalid class for exam");
		}

		List<ExamSubject> examSubjects = examSubjectRepository.findByExamId(examId);

		List<ExamSubjectDto> response = new ArrayList<>();

		for (ExamSubject es : examSubjects) {

			Subject subject = null;
			try {
				subject = SubjectRepository.findById(es.getSubjectId()).orElseThrow();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ExamSubjectDto dto = new ExamSubjectDto();

			dto.setSubjectId(subject.getId());
			dto.setSubjectName(subject.getSubjectName());
			dto.setMaxMarks(es.getMaxMarks());

			response.add(dto);
		}

		return response;
	}

	public List<StudentMarkResponse> getSavedMarks(Long schoolId, Long examId, Long subjectId, String className) {

		List<StudentMarks> marksList = marksRepository.findBySchoolIdAndExamIdAndSubjectIdAndClassName(schoolId, examId,
				subjectId, className);

		List<StudentMarkResponse> response = new ArrayList<>();

		for (StudentMarks mark : marksList) {

			StudentMarkResponse dto = new StudentMarkResponse();

			dto.setStudentId(mark.getStudentId());
			dto.setMarksObtained(mark.getMarksObtained());

			response.add(dto);
		}

		return response;
	}
}
