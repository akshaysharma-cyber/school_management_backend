package com.school.management.School.dto;

public class ExamDashboardDTO {
	
	private long upcomingExams;

    private long ongoingExams;

    private long totalStudents;

    private Double averageLastResult;

	

	public ExamDashboardDTO(long upcomingExams, long ongoingExams, long totalStudents, Double averageLastResult) {
		super();
		this.upcomingExams = upcomingExams;
		this.ongoingExams = ongoingExams;
		this.totalStudents = totalStudents;
		this.averageLastResult = averageLastResult;
	}

	public long getUpcomingExams() {
		return upcomingExams;
	}

	public void setUpcomingExams(long upcomingExams) {
		this.upcomingExams = upcomingExams;
	}

	public long getOngoingExams() {
		return ongoingExams;
	}

	public void setOngoingExams(long ongoingExams) {
		this.ongoingExams = ongoingExams;
	}

	public long getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(long totalStudents) {
		this.totalStudents = totalStudents;
	}

	public Double getAverageLastResult() {
		return averageLastResult;
	}

	public void setAverageLastResult(Double averageLastResult) {
		this.averageLastResult = averageLastResult;
	}

}
