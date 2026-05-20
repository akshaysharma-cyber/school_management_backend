package com.school.management.School.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.management.School.dto.ClassCollectionResponse;
import com.school.management.School.dto.RecentPaymentResponse;
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
	Double getMonthlyCollection(@Param("schoolId") Long schoolId, @Param("month") int month, @Param("year") int year);

	FeePayment findTopByStudentFeeIdOrderByIdDesc(Long studentFeeId);

	@Query("""
			SELECT new com.school.management.School.dto.RecentPaymentResponse(

			    st.fullName,

			    st.className,

			    fp.amountPaid,

			    fp.paymentDate,

			    sf.status

			)

			FROM FeePayment fp

			JOIN StudentFees sf
			ON fp.studentFeeId = sf.id

			JOIN Student st
			ON sf.studentId = st.id

			WHERE fp.schoolId = :schoolId

			ORDER BY fp.createdAt DESC
			""")
	List<RecentPaymentResponse> getRecentPayments(Long schoolId);
	
	@Query("""

			SELECT new com.school.management.School.dto.ClassCollectionResponse(

			    st.className,

			    SUM(fp.amountPaid)

			)

			FROM FeePayment fp

			JOIN StudentFees sf
			ON fp.studentFeeId = sf.id

			JOIN Student st
			ON sf.studentId = st.id

			WHERE fp.schoolId = :schoolId

			GROUP BY st.className

			""")
			List<ClassCollectionResponse>
			getClassCollection(Long schoolId);
}
