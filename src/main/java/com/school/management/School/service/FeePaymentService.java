package com.school.management.School.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.School.dto.ClassCollectionResponse;
import com.school.management.School.dto.CollectFeeRequest;
import com.school.management.School.dto.FeeDashboardResponse;
import com.school.management.School.dto.RecentPaymentResponse;
import com.school.management.School.entity.FeeDetailsResponse;
import com.school.management.School.entity.FeePayment;
import com.school.management.School.entity.StudentFees;
import com.school.management.School.repository.FeePaymentRepository;
import com.school.management.School.repository.StudentFeesRepository;

@Service
@Transactional
public class FeePaymentService {

	@Autowired
	private StudentFeesRepository studentFeesRepository;

	@Autowired
	private FeePaymentRepository feePaymentRepository;

	public String collectFee(CollectFeeRequest request) {

		StudentFees sf = studentFeesRepository.findById(request.getStudentFeeId())
				.orElseThrow(() -> new RuntimeException("Student fee not found"));

		if (request.getAmount() == null || request.getAmount() <= 0) {
			throw new RuntimeException("Enter valid amount");
		}

		if (request.getAmount() > sf.getDueAmount()) {
			throw new RuntimeException("Amount exceeds due amount");
		}

		// 🔹 Update amounts
		double newPaid = sf.getPaidAmount() + request.getAmount();
		double newDue = sf.getTotalAmount() - newPaid;

		sf.setPaidAmount(newPaid);
		sf.setDueAmount(newDue);

		if (newDue == 0) {
			sf.setStatus(StudentFees.Status.PAID);
		} else {
			sf.setStatus(StudentFees.Status.PARTIAL);
		}

		studentFeesRepository.save(sf);

		// 🔹 Save payment
		FeePayment payment = new FeePayment();

		payment.setStudentFeeId(sf.getId());

		payment.setSchoolId(sf.getSchoolId());

		payment.setAcademicYear(sf.getAcademicYear());

		payment.setAmountPaid(request.getAmount());

		if (request.getPaymentDate() == null ||
		    request.getPaymentDate().isEmpty()) {

		    throw new RuntimeException("Payment date is required");
		}

		payment.setPaymentDate(LocalDate.parse(request.getPaymentDate()));

		payment.setPaymentMode(request.getPaymentMode());

		payment.setRemarks(request.getRemarks());


		// SAVE FIRST
		FeePayment savedPayment = feePaymentRepository.save(payment);


		// GENERATE RECEIPT NUMBER
		String receiptNumber =
		        "RCPT-" +
		        LocalDate.now().getYear() +
		        "-" +
		        savedPayment.getId();


		// UPDATE RECEIPT NUMBER
		savedPayment.setReceiptNumber(receiptNumber);

		feePaymentRepository.save(savedPayment);


		// RETURN RECEIPT NUMBER
		return receiptNumber;
	}
	
	
	public FeeDetailsResponse getFeeDetails(
	        Long schoolId,
	        Long studentId
	) {

	    StudentFees fee = studentFeesRepository
	            .findBySchoolIdAndStudentId(
	                    schoolId,
	                    studentId
	            )
	            .orElseThrow(() ->
	                    new RuntimeException(
	                            "Fee details not found"
	                    ));



	    // FETCH LATEST PAYMENT
	    FeePayment latestPayment =
	            feePaymentRepository
	                    .findTopByStudentFeeIdOrderByIdDesc(
	                            fee.getId()
	                    );



	    String receiptNumber = "";

	    if (latestPayment != null) {

	        receiptNumber =
	                latestPayment.getReceiptNumber();
	    }



	    return new FeeDetailsResponse(

	            fee.getId(),

	            fee.getTotalAmount(),

	            fee.getPaidAmount(),

	            fee.getDueAmount(),

	            receiptNumber
	    );
	}
	
	public FeeDashboardResponse getDashboardData(
	        Long schoolId
	) {

	    Double totalFeesDue =
	            studentFeesRepository
	                    .getTotalFeesDue(schoolId);

	    Double feesCollected =
	            studentFeesRepository
	                    .getFeesCollected(schoolId);

	    Double pendingAmount =
	            studentFeesRepository
	                    .getPendingAmount(schoolId);

	    Double collectionRate = 0.0;

	    if (totalFeesDue > 0) {

	        collectionRate =
	                (feesCollected / totalFeesDue) * 100;
	    }

	    return new FeeDashboardResponse(

	            totalFeesDue,

	            feesCollected,

	            pendingAmount,

	            Math.round(collectionRate * 100.0) / 100.0
	    );
	}
	
	public List<RecentPaymentResponse>
	getRecentPayments(Long schoolId) {

	    return feePaymentRepository
	            .getRecentPayments(schoolId);
	}
	
	public List<ClassCollectionResponse>
	getClassCollection(Long schoolId) {

	    return feePaymentRepository
	            .getClassCollection(schoolId);
	}

}
