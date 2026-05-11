package com.school.management.School.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.dto.StudentRequest;
import com.school.management.School.entity.Student;
import com.school.management.School.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
    private StudentRepository studentRepository;

    public String addStudent(StudentRequest request) {

        if (studentRepository.findByAdmissionNumber(request.getAdmissionNumber()).isPresent()) {
            throw new RuntimeException("Admission number already exists");
        }

        Student student = new Student();
        student.setSchoolId(request.getSchoolId());
        student.setAdmissionNumber(request.getAdmissionNumber());
        student.setFullName(request.getFullName());

        if (request.getDateOfBirth() != null) {
            student.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        }

        student.setGender(request.getGender());
        student.setBloodGroup(request.getBloodGroup());
        student.setCategory(request.getCategory());
        student.setReligion(request.getReligion());
        student.setNationality(request.getNationality());

        student.setParentName(request.getParentName());
        student.setRelationship(request.getRelationship());
        student.setParentMobile(request.getParentMobile());
        student.setParentEmail(request.getParentEmail());
        student.setAddress(request.getAddress());

        student.setClassName(request.getClassName());
        student.setSection(request.getSection());

        studentRepository.save(student);

        return "Student added successfully";
    }
    
    public List<Student> getStudentsByClass(
            Long schoolId,
            String className
    ) {
        return studentRepository.findBySchoolIdAndClassName(
                schoolId,
                className
        );
    }
}
