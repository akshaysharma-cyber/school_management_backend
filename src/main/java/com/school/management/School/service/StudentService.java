package com.school.management.School.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.dto.StudentRequest;
import com.school.management.School.entity.Student;
import com.school.management.School.entity.StudentResponse;
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
        student.setActive(true);
       

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
    
    public List<StudentResponse> getStudentsByClassForMarks(
            Long schoolId,
            String className
    ) {

        List<Student> students =
                studentRepository.findBySchoolIdAndClassName(
                        schoolId,
                        className
                );

        return students.stream().map(student -> {

            StudentResponse response =
                    new StudentResponse();

            response.setId(student.getId());
            response.setStudentName(student.getFullName());
            response.setAdmissionNo(student.getAdmissionNumber());
            

            return response;

        }).toList();
    }
    
    
 // =========================
    // GET ALL STUDENTS
    // =========================
    public List<Student> getAllStudents(Long schoolId) {
        return studentRepository.findBySchoolId(schoolId);
    }

    // =========================
    // GET STUDENT BY ID
    // =========================
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // =========================
    // UPDATE STUDENT
    // =========================
    public Student updateStudent(Long id, Student updatedStudent) {

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {

            Student student = optionalStudent.get();

            //student.setAdmissionNumber(updatedStudent.getAdmissionNumber());
            student.setFullName(updatedStudent.getFullName());
            student.setDateOfBirth(updatedStudent.getDateOfBirth());
            student.setGender(updatedStudent.getGender());
            student.setBloodGroup(updatedStudent.getBloodGroup());

            student.setCategory(updatedStudent.getCategory());
            student.setReligion(updatedStudent.getReligion());
            student.setNationality(updatedStudent.getNationality());

            student.setParentName(updatedStudent.getParentName());
            student.setRelationship(updatedStudent.getRelationship());
            student.setParentMobile(updatedStudent.getParentMobile());
            student.setParentEmail(updatedStudent.getParentEmail());

            student.setAddress(updatedStudent.getAddress());

            student.setClassName(updatedStudent.getClassName());
            student.setSection(updatedStudent.getSection());
            student.setActive(false);
            return studentRepository.save(student);
        }

        return null;
    }

    // =========================
    // DELETE STUDENT
    // =========================
    public String deleteStudent(Long id) {

        if (studentRepository.existsById(id)) {

            studentRepository.deleteById(id);

            return "Student Deleted Successfully";
        }

        return "Student Not Found";
    }
}
