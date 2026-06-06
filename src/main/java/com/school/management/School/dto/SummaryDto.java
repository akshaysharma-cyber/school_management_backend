package com.school.management.School.dto;

public class SummaryDto {

	private double average;
    private double highest;
    private double lowest;
    private double passPercentage;
    private long passedStudents;
    private long failedStudents;
	public long getPassedStudents() {
		return passedStudents;
	}
	public void setPassedStudents(long passedStudents) {
		this.passedStudents = passedStudents;
	}
	public long getFailedStudents() {
		return failedStudents;
	}
	public void setFailedStudents(long failedStudents) {
		this.failedStudents = failedStudents;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public double getHighest() {
		return highest;
	}
	public void setHighest(double highest) {
		this.highest = highest;
	}
	public double getLowest() {
		return lowest;
	}
	public void setLowest(double lowest) {
		this.lowest = lowest;
	}
	public double getPassPercentage() {
		return passPercentage;
	}
	public void setPassPercentage(double passPercentage) {
		this.passPercentage = passPercentage;
	}
}
