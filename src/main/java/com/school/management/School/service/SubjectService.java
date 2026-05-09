package com.school.management.School.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.entity.ClassSubject;
import com.school.management.School.repository.ClassSubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
    private ClassSubjectRepository classSubjectRepository;

    public List<String> getSubjectsByClass(Long schoolId, String className) {

        List<ClassSubject> list =
                classSubjectRepository.findBySchoolIdAndClassName(schoolId, className);

        if (list.isEmpty()) {
            throw new RuntimeException("No subjects found for this class");
        }

        return list.stream()
                .map(cs -> cs.getSubject().getSubjectName())
                .toList();
    }

}
