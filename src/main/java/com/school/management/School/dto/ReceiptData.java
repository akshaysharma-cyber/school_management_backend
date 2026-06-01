package com.school.management.School.dto;

public class ReceiptData {

	    private String studentName;
	    private String fatherName;
	    private String mobile;
	    private String className;
	    private String section;
	    private String receiptNumber;
	    private String paymentDate;
	    private String paymentMode;
	    private Double totalAmount;
	    private Double paidAmount;
	    private Double dueAmount;
	    private Double currentPaid;
	    
		public String getStudentName() {
			return studentName;
		}
		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}
		public String getFatherName() {
			return fatherName;
		}
		public void setFatherName(String fatherName) {
			this.fatherName = fatherName;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getSection() {
			return section;
		}
		public void setSection(String section) {
			this.section = section;
		}
		public String getReceiptNumber() {
			return receiptNumber;
		}
		public void setReceiptNumber(String receiptNumber) {
			this.receiptNumber = receiptNumber;
		}
		public String getPaymentDate() {
			return paymentDate;
		}
		public void setPaymentDate(String paymentDate) {
			this.paymentDate = paymentDate;
		}
		public String getPaymentMode() {
			return paymentMode;
		}
		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}
		public Double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public Double getPaidAmount() {
			return paidAmount;
		}
		public void setPaidAmount(Double paidAmount) {
			this.paidAmount = paidAmount;
		}
		public Double getDueAmount() {
			return dueAmount;
		}
		public void setDueAmount(Double dueAmount) {
			this.dueAmount = dueAmount;
		}
		public Double getCurrentPaid() {
			return currentPaid;
		}
		public void setCurrentPaid(Double currentPaid) {
			this.currentPaid = currentPaid;
		}
	
}
