package com.school.management.School.dto;

import java.util.List;

public class CreateExamRequest {
	private Long schoolId;
	private String examName;
	private String examType;
	private String className;
	private String academicYear;
	private String startDate;
	private String endDate;
	private String description;
	private Double passingPercentage;
	private String gradingSystem;
	private String resultPublishDate;
	private Boolean allowReexam;
	private Boolean allowMarksEntry;
	private Boolean sendSms;

	private List<SubjectDto> subjects;

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPassingPercentage() {
		return passingPercentage;
	}

	public void setPassingPercentage(Double passingPercentage) {
		this.passingPercentage = passingPercentage;
	}

	public String getGradingSystem() {
		return gradingSystem;
	}

	public void setGradingSystem(String gradingSystem) {
		this.gradingSystem = gradingSystem;
	}

	public String getResultPublishDate() {
		return resultPublishDate;
	}

	public void setResultPublishDate(String resultPublishDate) {
		this.resultPublishDate = resultPublishDate;
	}

	public Boolean getAllowReexam() {
		return allowReexam;
	}

	public void setAllowReexam(Boolean allowReexam) {
		this.allowReexam = allowReexam;
	}

	public Boolean getAllowMarksEntry() {
		return allowMarksEntry;
	}

	public void setAllowMarksEntry(Boolean allowMarksEntry) {
		this.allowMarksEntry = allowMarksEntry;
	}

	public Boolean getSendSms() {
		return sendSms;
	}

	public void setSendSms(Boolean sendSms) {
		this.sendSms = sendSms;
	}

	public List<SubjectDto> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}

}
