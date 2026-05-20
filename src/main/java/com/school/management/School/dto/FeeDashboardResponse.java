package com.school.management.School.dto;

public class FeeDashboardResponse {
	
	 private Double totalFeesDue;

	    private Double feesCollected;

	    private Double pendingAmount;

	    private Double collectionRate;

	    public FeeDashboardResponse(
	            Double totalFeesDue,
	            Double feesCollected,
	            Double pendingAmount,
	            Double collectionRate
	    ) {

	        this.totalFeesDue = totalFeesDue;

	        this.feesCollected = feesCollected;

	        this.pendingAmount = pendingAmount;

	        this.collectionRate = collectionRate;
	    }

		public Double getTotalFeesDue() {
			return totalFeesDue;
		}

		public void setTotalFeesDue(Double totalFeesDue) {
			this.totalFeesDue = totalFeesDue;
		}

		public Double getFeesCollected() {
			return feesCollected;
		}

		public void setFeesCollected(Double feesCollected) {
			this.feesCollected = feesCollected;
		}

		public Double getPendingAmount() {
			return pendingAmount;
		}

		public void setPendingAmount(Double pendingAmount) {
			this.pendingAmount = pendingAmount;
		}

		public Double getCollectionRate() {
			return collectionRate;
		}

		public void setCollectionRate(Double collectionRate) {
			this.collectionRate = collectionRate;
		}

}
