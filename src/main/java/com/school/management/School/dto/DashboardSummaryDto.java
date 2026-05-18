package com.school.management.School.dto;

public class DashboardSummaryDto {

	
	 private long totalStudents;
	    private long totalTeachers;
	    private long presentToday;
	    private double feesCollectedThisMonth;

	    public DashboardSummaryDto() {
	    }

	    public DashboardSummaryDto(long totalStudents,
	                               long totalTeachers,
	                               long presentToday,
	                               double feesCollectedThisMonth) {
	        this.totalStudents = totalStudents;
	        this.totalTeachers = totalTeachers;
	        this.presentToday = presentToday;
	        this.feesCollectedThisMonth = feesCollectedThisMonth;
	    }

	    public long getTotalStudents() {
	        return totalStudents;
	    }

	    public void setTotalStudents(long totalStudents) {
	        this.totalStudents = totalStudents;
	    }

	    public long getTotalTeachers() {
	        return totalTeachers;
	    }

	    public void setTotalTeachers(long totalTeachers) {
	        this.totalTeachers = totalTeachers;
	    }

	    public long getPresentToday() {
	        return presentToday;
	    }

	    public void setPresentToday(long presentToday) {
	        this.presentToday = presentToday;
	    }

	    public double getFeesCollectedThisMonth() {
	        return feesCollectedThisMonth;
	    }

	    public void setFeesCollectedThisMonth(double feesCollectedThisMonth) {
	        this.feesCollectedThisMonth = feesCollectedThisMonth;
	    }
}
