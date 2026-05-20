package com.school.management.School.dto;

import java.time.LocalDate;

import com.school.management.School.entity.StudentFees;

public class RecentPaymentResponse {
	
	 private String studentName;

	    private String className;

	    private Double amount;

	    private LocalDate paymentDate;

	    public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public LocalDate getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(LocalDate paymentDate) {
			this.paymentDate = paymentDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		private String status;

		public RecentPaymentResponse(
		        String studentName,
		        String className,
		        Double amount,
		        LocalDate paymentDate,
		        StudentFees.Status status
		) {

		    this.studentName = studentName;

		    this.className = className;

		    this.amount = amount;

		    this.paymentDate = paymentDate;

		    this.status = status.toString();
		}
	    
	    public RecentPaymentResponse() {
	    }

}
