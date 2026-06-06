package com.school.management.School.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.dto.ResultResponse;
import com.school.management.School.dto.StudentResultDto;
import com.school.management.School.dto.SubjectDto;
import com.school.management.School.dto.SubjectMarksDto;
import com.school.management.School.dto.SummaryDto;
import com.school.management.School.entity.Exam;
import com.school.management.School.entity.ExamSubject;
import com.school.management.School.entity.Student;
import com.school.management.School.entity.StudentMarks;
import com.school.management.School.entity.Subject;
import com.school.management.School.repository.ExamRepository;
import com.school.management.School.repository.ExamSubjectRepository;
import com.school.management.School.repository.StudentMarksRepository;
import com.school.management.School.repository.StudentRepository;
import com.school.management.School.repository.SubjectRepository;

@Service
public class ResultService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentMarksRepository marksRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private ExamSubjectRepository examSubjectRepository;
	@Autowired
	private ExamRepository examRepository;

	public ResultResponse getResult(Long schoolId, String academicYear, String examType, String className) {

		Exam exam = examRepository
				.findBySchoolIdAndAcademicYearAndExamTypeAndClassName(schoolId, academicYear, examType, className)
				.orElseThrow(() -> new RuntimeException("Exam not found"));

		Long examId = exam.getId();

		List<Student> students = studentRepository.findBySchoolIdAndClassName(schoolId, className);

		List<ExamSubject> examSubjects = examSubjectRepository.findByExamId(examId);

		// 🔹 Subjects List
		List<SubjectDto> subjectList = new ArrayList<>();

		for (ExamSubject es : examSubjects) {

			Subject sub = subjectRepository.findById(es.getSubjectId()).orElse(null);

			SubjectDto dto = new SubjectDto();
			dto.setSubjectId(es.getSubjectId());
			dto.setSubjectName(sub != null ? sub.getSubjectName() : "N/A");
			dto.setMaxMarks(es.getMaxMarks());

			subjectList.add(dto);
		}

		List<StudentResultDto> resultList = new ArrayList<>();

		// 🔹 Student loop
		for (Student s : students) {

			List<StudentMarks> marksList = marksRepository.findByExamIdAndStudentId(examId, s.getId());

			double total = 0;
			double totalMax = 0;

			List<SubjectMarksDto> subjectMarks = new ArrayList<>();

			for (ExamSubject es : examSubjects) {

				totalMax += es.getMaxMarks();

				StudentMarks mark = marksList.stream().filter(m -> m.getSubjectId().equals(es.getSubjectId()))
						.findFirst().orElse(null);

				double obtained = (mark != null) ? mark.getMarksObtained() : 0;

				total += obtained;

				SubjectMarksDto sm = new SubjectMarksDto();
				sm.setSubjectId(es.getSubjectId());
				sm.setMarks(obtained);

				subjectMarks.add(sm);
			}

			double percentage = totalMax > 0 ? (total / totalMax) * 100 : 0;

			StudentResultDto dto = new StudentResultDto();
			dto.setStudentId(s.getId());
			dto.setName(s.getFullName()); // according to your table
			dto.setRollNo(s.getAdmissionNumber()); // using admission_number

			dto.setMarks(subjectMarks);
			dto.setTotal(total);
			dto.setPercentage(percentage);
			dto.setGrade(getGrade(percentage));
			dto.setResult(percentage >= 33 ? "Passed" : "Failed");

			resultList.add(dto);
		}

		// 🔹 Summary
		SummaryDto summary = new SummaryDto();

		double avg = resultList.stream().mapToDouble(StudentResultDto::getPercentage).average().orElse(0);

		double max = resultList.stream().mapToDouble(StudentResultDto::getPercentage).max().orElse(0);

		double min = resultList.stream().mapToDouble(StudentResultDto::getPercentage).min().orElse(0);

		long passed = resultList.stream().filter(r -> r.getResult().equals("Passed")).count();

		long failed = resultList.size() - passed;

		summary.setAverage(avg);
		summary.setHighest(max);
		summary.setLowest(min);

		summary.setPassedStudents(passed);
		summary.setFailedStudents(failed);

		summary.setPassPercentage(resultList.isEmpty() ? 0 : (passed * 100.0) / resultList.size());

		ResultResponse response = new ResultResponse();
		response.setSummary(summary);
		response.setSubjects(subjectList);
		response.setStudents(resultList);

		return response;
	}

	private String getGrade(double p) {
		if (p >= 90)
			return "A1";
		else if (p >= 80)
			return "A";
		else if (p >= 70)
			return "B";
		else if (p >= 60)
			return "C";
		else if (p >= 50)
			return "D";
		else
			return "F";
	}

}
