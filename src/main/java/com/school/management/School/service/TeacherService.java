package com.school.management.School.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.School.dto.CreateTeacherRequest;
import com.school.management.School.entity.Role;
import com.school.management.School.entity.School;
import com.school.management.School.entity.TeacherDetails;
import com.school.management.School.entity.User;
import com.school.management.School.repository.SchoolRepository;
import com.school.management.School.repository.TeacherRepository;
import com.school.management.School.repository.UserRepository;

@Service
public class TeacherService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Transactional
    public String createTeacher(CreateTeacherRequest request) {

        // 🔹 1. Validate School
        School school = schoolRepository.findById(request.getSchoolId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        // 🔹 2. Validate Required Fields
        if (request.getFullName() == null || request.getFullName().isEmpty()) {
            throw new RuntimeException("Full name is required");
        }

        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        if (request.getMobile() == null || request.getMobile().isEmpty()) {
            throw new RuntimeException("Mobile number is required");
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        if (request.getEmployeeId() == null || request.getEmployeeId().isEmpty()) {
            throw new RuntimeException("Employee ID is required");
        }

        // 🔹 3. Check Duplicates
        if (teacherRepository.findByEmployeeId(request.getEmployeeId()).isPresent()) {
            throw new RuntimeException("Employee ID already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByMobile(request.getMobile())) {
            throw new RuntimeException("Mobile already exists");
        }

        // 🔹 4. Create User (Login Table)
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.TEACHER);
        user.setSchool(school);

        user = userRepository.save(user);

        // 🔹 5. Create Teacher Details
        TeacherDetails teacher = new TeacherDetails();
        teacher.setUser(user);
        teacher.setSchoolId(request.getSchoolId());
        teacher.setEmployeeId(request.getEmployeeId());
        teacher.setQualification(request.getQualification());
        teacher.setSubject(request.getSubject());

        // Date parsing
        if (request.getJoiningDate() != null && !request.getJoiningDate().isEmpty()) {
            teacher.setJoiningDate(LocalDate.parse(request.getJoiningDate()));
        }

        if (request.getDateOfBirth() != null && !request.getDateOfBirth().isEmpty()) {
            teacher.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        }

        teacher.setGender(request.getGender());
        teacher.setAddress(request.getAddress());

        teacherRepository.save(teacher);

        return "Teacher added successfully";
    }
    
    
    // GET ALL TEACHERS
    public List<TeacherDetails> getAllTeachers(Long schoolId) {
        return teacherRepository.findBySchoolId(schoolId);
    }

    // GET SINGLE TEACHER
    public TeacherDetails getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    // UPDATE TEACHER
    public TeacherDetails updateTeacher(Long id, TeacherDetails updatedTeacher) {

        Optional<TeacherDetails> optionalTeacher = teacherRepository.findById(id);

        if(optionalTeacher.isPresent()) {

            TeacherDetails teacher = optionalTeacher.get();

            teacher.setFullName(updatedTeacher.getFullName());
            teacher.setMobileNumber(updatedTeacher.getMobileNumber());
            teacher.setDateOfBirth(updatedTeacher.getDateOfBirth());
            teacher.setGender(updatedTeacher.getGender());
            teacher.setAddress(updatedTeacher.getAddress());

            teacher.setEmployeeId(updatedTeacher.getEmployeeId());
            teacher.setQualification(updatedTeacher.getQualification());
            teacher.setSubject(updatedTeacher.getSubject());
            teacher.setJoiningDate(updatedTeacher.getJoiningDate());

            return teacherRepository.save(teacher);
        }

        return null;
    }

    // DELETE TEACHER
    public String deleteTeacher(Long id) {

        teacherRepository.deleteById(id);

        return "Teacher Deleted Successfully";
    }

}
