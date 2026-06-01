package com.school.management.School.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.management.School.entity.StudentFees;

public interface StudentFeesRepository extends JpaRepository<StudentFees, Long> {
	 // 🔹 Used in assign API (avoid duplicate)
    Optional<StudentFees> findByStudentIdAndAcademicYear(
            Long studentId,
            String academicYear
    );

    // 🔹 Fetch all students of class (dashboard/report)
    List<StudentFees> findByClassNameAndSectionAndAcademicYear(
            String className,
            String section,
            String academicYear
    );

    // 🔹 Get all pending students
    List<StudentFees> findByStatusAndAcademicYear(
            StudentFees.Status status,
            String academicYear
    );
    
    Optional<StudentFees> findBySchoolIdAndStudentId(
            Long schoolId,
            Long studentId
    );
    
    @Query("""
    	       SELECT COALESCE(SUM(s.totalAmount),0)
    	       FROM StudentFees s
    	       WHERE s.schoolId = :schoolId
    	       """)
    	Double getTotalFeesDue(Long schoolId);
    
    @Query("""
    	       SELECT COALESCE(SUM(s.paidAmount),0)
    	       FROM StudentFees s
    	       WHERE s.schoolId = :schoolId
    	       """)
    	Double getFeesCollected(Long schoolId);
    
    @Query("""
    	       SELECT COALESCE(SUM(s.dueAmount),0)
    	       FROM StudentFees s
    	       WHERE s.schoolId = :schoolId
    	       """)
    	Double getPendingAmount(Long schoolId);
    
   //using in fee assignment 
    Optional<StudentFees>
    findByStudentIdAndSchoolIdAndAcademicYear(
            Long studentId,
            Long schoolId,
            String academicYear
    );
}
