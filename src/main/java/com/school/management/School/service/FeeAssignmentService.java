package com.school.management.School.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.School.dto.AssignFeeRequest;
import com.school.management.School.entity.FeeStructure;
import com.school.management.School.entity.Student;
import com.school.management.School.entity.StudentFees;
import com.school.management.School.repository.FeeStructureRepository;
import com.school.management.School.repository.StudentFeesRepository;
import com.school.management.School.repository.StudentRepository;

@Service
@Transactional
public class FeeAssignmentService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private FeeStructureRepository feeStructureRepository;

	@Autowired
	private StudentFeesRepository studentFeesRepository;

	public String assignFeeToClass(AssignFeeRequest request) {

		// 🔹 1. Get Fee Structure
		FeeStructure fs = feeStructureRepository.findBySchoolIdAndClassNameAndAcademicYear(request.getSchoolId(),
				request.getClassName(), request.getAcademicYear())
				.orElseThrow(() -> new RuntimeException("Fee structure not found"));

		// 🔹 2. Get Students
		List<Student> students = studentRepository.findByClassNameAndSection(request.getClassName(),
				request.getSection());

		if (students.isEmpty()) {
			throw new RuntimeException("No students found");
		}

		// 🔹 3. Assign Fee to Each Student
		for (Student student : students) {

			// prevent duplicate
			boolean exists = studentFeesRepository
					.findByStudentIdAndAcademicYear(student.getId(), request.getAcademicYear()).isPresent();

			if (exists)
				continue;

			StudentFees sf = new StudentFees();

			sf.setStudentId(student.getId());
			sf.setSchoolId(request.getSchoolId());
			sf.setFeeStructureId(fs.getId());

			sf.setClassName(student.getClassName());
			sf.setSection(student.getSection());
			sf.setAcademicYear(request.getAcademicYear());

			sf.setTotalAmount(fs.getTotalAmount());
			sf.setPaidAmount(0.0);
			sf.setDueAmount(fs.getTotalAmount());
			sf.setStatus(StudentFees.Status.PENDING);

			studentFeesRepository.save(sf);
		}

		return "Fee assigned to class successfully";
	}
}
