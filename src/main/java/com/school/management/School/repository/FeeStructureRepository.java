package com.school.management.School.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.school.management.School.entity.FeeStructure;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {
	Optional<FeeStructure> findBySchoolIdAndClassNameAndAcademicYear(
            Long schoolId, String className, String academicYear);
	
	

}
