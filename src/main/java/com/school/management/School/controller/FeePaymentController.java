package com.school.management.School.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.School.dto.ClassCollectionResponse;
import com.school.management.School.dto.CollectFeeRequest;
import com.school.management.School.dto.FeeDashboardResponse;
import com.school.management.School.dto.RecentPaymentResponse;
import com.school.management.School.entity.FeeDetailsResponse;
import com.school.management.School.entity.FeePayment;
import com.school.management.School.entity.StudentFees;
import com.school.management.School.repository.FeePaymentRepository;
import com.school.management.School.repository.StudentFeesRepository;
import com.school.management.School.service.FeePaymentService;

@RestController
@RequestMapping("/api/fees")
public class FeePaymentController {
	@Autowired
    private FeePaymentService feePaymentService;
	@Autowired
	private StudentFeesRepository feesRepository;
	@Autowired
    private FeePaymentRepository feePaymentRepository;
	
	

	@PostMapping("/collect")
	public ResponseEntity<?> collectFee(
	        @RequestBody CollectFeeRequest request) {

	    return ResponseEntity.ok(
	            feePaymentService.collectFee(request));
	}
    
    @GetMapping("/student/{id}")
    public Optional<StudentFees> getStudentFee(@PathVariable Long id) throws RuntimeException {
        
		return feesRepository.findById(id);
    }
    
    @GetMapping("/student/{schoolId}/{studentId}")
    public FeeDetailsResponse getFeeDetails(
            @PathVariable Long schoolId,
            @PathVariable Long studentId
    ) {

        return feePaymentService.getFeeDetails(
                schoolId,
                studentId
        );
    }
    
    @GetMapping("/dashboard/{schoolId}")
    public FeeDashboardResponse getDashboardData(
            @PathVariable Long schoolId
    ) {

        return feePaymentService
                .getDashboardData(schoolId);
    }
    
    @GetMapping("/recent-payments/{schoolId}")
    public List<RecentPaymentResponse>
    getRecentPayments(
            @PathVariable Long schoolId
    ) {

        return feePaymentService
                .getRecentPayments(schoolId);
    }
    
    @GetMapping("/class-collection/{schoolId}")
    public List<ClassCollectionResponse>
    getClassCollection(
            @PathVariable Long schoolId
    ) {

        return feePaymentService
                .getClassCollection(schoolId);
    }
    
    // =========================================
    // PAYMENT HISTORY
    // =========================================

    @GetMapping(
            "/payment-history/{schoolId}/{studentFeeId}"
    )

    public ResponseEntity<?> getPaymentHistory(

            @PathVariable Long schoolId,

            @PathVariable Long studentFeeId

    ) {

        List<FeePayment> payments =

                feePaymentRepository
                        .findBySchoolIdAndStudentFeeIdOrderByPaymentDateAsc(
                                schoolId,
                                studentFeeId
                        );

        return ResponseEntity.ok(
                payments
        );
    }

}
