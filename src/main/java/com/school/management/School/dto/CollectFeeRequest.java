package com.school.management.School.dto;

public class CollectFeeRequest {
	private Long studentFeeId;
    private Double amount;
    private String paymentDate;
    private String paymentMode;
    private String remarks;
	public Long getStudentFeeId() {
		return studentFeeId;
	}
	public void setStudentFeeId(Long studentFeeId) {
		this.studentFeeId = studentFeeId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
