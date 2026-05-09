package com.school.management.School.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.management.School.entity.FeePayment;

public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
	 List<FeePayment> findByStudentFeeId(Long studentFeeId);
}
