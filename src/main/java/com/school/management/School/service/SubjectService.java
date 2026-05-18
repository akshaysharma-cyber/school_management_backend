package com.school.management.School.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.School.entity.ClassSubject;
import com.school.management.School.repository.ClassSubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
    private ClassSubjectRepository classSubjectRepository;

	public List<Map<String, Object>> getSubjectsByClass(Long schoolId, String className) {

	    List<ClassSubject> list =
	            classSubjectRepository.findBySchoolIdAndClassName(schoolId, className);

	    if (list.isEmpty()) {
	        return new ArrayList<>();
	    }

	    return list.stream()
	            .map(cs -> {
	                Map<String, Object> subjectMap = new HashMap<>();
	                subjectMap.put("id", cs.getSubject().getId());
	                subjectMap.put("subjectName", cs.getSubject().getSubjectName());
	                return subjectMap;
	            })
	            .toList();
	}

}
