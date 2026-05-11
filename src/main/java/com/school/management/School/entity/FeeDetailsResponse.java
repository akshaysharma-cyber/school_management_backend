package com.school.management.School.entity;

public class FeeDetailsResponse {
	private Long id;
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Double totalAmount;
	    private Double paidAmount;
	    private Double dueAmount;

	    public FeeDetailsResponse() {
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

		public FeeDetailsResponse(Long id,Double totalAmount, Double paidAmount, Double dueAmount) {
	        this.id=id;
			this.totalAmount = totalAmount;
	        this.paidAmount = paidAmount;
	        this.dueAmount = dueAmount;
	    }

}
