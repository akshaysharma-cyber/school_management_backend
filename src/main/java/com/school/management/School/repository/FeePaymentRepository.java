package com.school.management.School.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.management.School.entity.FeePayment;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
	 List<FeePayment> findByStudentFeeId(Long studentFeeId);
	 
	 @Query("""
		        SELECT COALESCE(SUM(f.amountPaid), 0)
		        FROM FeePayment f
		        WHERE f.schoolId = :schoolId
		        AND FUNCTION('MONTH', f.paymentDate) = :month
		        AND FUNCTION('YEAR', f.paymentDate) = :year
		    """)
		    Double getMonthlyCollection(
		            @Param("schoolId") Long schoolId,
		            @Param("month") int month,
		            @Param("year") int year
		    );
}
