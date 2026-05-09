package com.school.management.School.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.School.dto.MarksEntryRequest;
import com.school.management.School.entity.Exam;
import com.school.management.School.entity.StudentMarks;
import com.school.management.School.repository.ExamRepository;
import com.school.management.School.repository.ExamSubjectRepository;
import com.school.management.School.repository.StudentMarksRepository;
import com.school.management.School.repository.StudentRepository;

@Service
@Transactional
public class MarksService {
	
	 @Autowired private StudentMarksRepository marksRepository;
	    @Autowired private StudentRepository studentRepository;
	    @Autowired private ExamRepository examRepository;
	    @Autowired private ExamSubjectRepository examSubjectRepository;

	    public String saveMarks(MarksEntryRequest request) {

	        // ✅ 1. Validate exam
	        Exam exam = examRepository.findById(request.getExamId())
	                .orElseThrow(() -> new RuntimeException("Exam not found"));

	        if (!exam.getSchoolId().equals(request.getSchoolId()) ||
	            !exam.getClassName().equals(request.getClassName()) ||
	            !exam.getSection().equals(request.getSection())) {

	            throw new RuntimeException("Invalid exam mapping");
	        }

	        // ✅ 2. Validate subject
	        if (!examSubjectRepository.existsByExamIdAndSubjectId(
	                request.getExamId(),
	                request.getSubjectId())) {

	            throw new RuntimeException("Subject not part of exam");
	        }

	        // ✅ 3. Save marks
	        for (MarksEntryRequest.StudentMarksDto s : request.getMarks()) {

	            // ✅ student validation
	            if (!studentRepository.existsByIdAndSchoolIdAndClassNameAndSection(
	                    s.getStudentId(),
	                    request.getSchoolId(),
	                    request.getClassName(),
	                    request.getSection())) {

	                throw new RuntimeException("Invalid student: " + s.getStudentId());
	            }

	            StudentMarks entity = marksRepository
	                    .findBySchoolIdAndExamIdAndStudentIdAndSubjectId(
	                            request.getSchoolId(),
	                            request.getExamId(),
	                            s.getStudentId(),
	                            request.getSubjectId()
	                    )
	                    .orElse(new StudentMarks());

	            entity.setSchoolId(request.getSchoolId());
	            entity.setExamId(request.getExamId());
	            entity.setStudentId(s.getStudentId());
	            entity.setSubjectId(request.getSubjectId());
	            entity.setMarksObtained(s.getMarksObtained());
	            entity.setClassName(request.getClassName());
	            entity.setSection(request.getSection());

	            marksRepository.save(entity);
	        }

	        return "Marks saved successfully";
	    }

}
