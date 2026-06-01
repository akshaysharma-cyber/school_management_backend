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

	public String assignFeeToClass(
	        AssignFeeRequest request
	) {

	    // =========================
	    // GET FEE STRUCTURE
	    // =========================

	    FeeStructure fs =
	            feeStructureRepository
	            .findBySchoolIdAndClassNameAndAcademicYear(
	                    request.getSchoolId(),
	                    request.getClassName(),
	                    request.getAcademicYear()
	            )
	            .orElseThrow(() ->
	                    new RuntimeException(
	                            "Fee structure not found"
	                    )
	            );

	    // =========================
	    // GET STUDENTS OF SAME SCHOOL
	    // =========================

	    List<Student> students =
	            studentRepository
	            .findBySchoolIdAndClassName(
	                    request.getSchoolId(),
	                    request.getClassName()
	            );

	    if (students.isEmpty()) {

	        throw new RuntimeException(
	                "No students found"
	        );
	    }

	    // =========================
	    // ASSIGN FEES
	    // =========================

	    int assignedCount = 0;

	    for (Student student : students) {

	        boolean exists =
	                studentFeesRepository
	                .findByStudentIdAndSchoolIdAndAcademicYear(
	                        student.getId(),
	                        request.getSchoolId(),
	                        request.getAcademicYear()
	                )
	                .isPresent();

	        if (exists) {
	            continue;
	        }

	        StudentFees sf = new StudentFees();

	        sf.setStudentId(student.getId());

	        sf.setSchoolId(request.getSchoolId());

	        sf.setFeeStructureId(fs.getId());

	        sf.setClassName(student.getClassName());

	        sf.setAcademicYear(
	                request.getAcademicYear()
	        );

	        sf.setTotalAmount(
	                fs.getTotalAmount()
	        );

	        sf.setPaidAmount(0.0);

	        sf.setDueAmount(
	                fs.getTotalAmount()
	        );

	        sf.setStatus(
	                StudentFees.Status.PENDING
	        );

	        studentFeesRepository.save(sf);

	        assignedCount++;
	    }

	    // =========================
	    // RESPONSE
	    // =========================

	    if (assignedCount == 0) {

	        return "Fee already assigned to all students";
	    }

	    return "Fee assigned successfully to "
	            + assignedCount
	            + " students";
	}
}
