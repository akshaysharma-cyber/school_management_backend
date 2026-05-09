package com.school.management.School.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long schoolId;

    private String examName;
    private String examType;

    private String className;
    private String section;

    private String academicYear;

    private LocalDate startDate;
    private LocalDate endDate;

    private String description;

    private Double passingPercentage;
    private String gradingSystem;

    private Integer totalMarks;

    private LocalDate resultPublishDate;

    private Boolean allowReexam;
    private Boolean allowMarksEntry;
    private Boolean sendSms;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamSubject> subjects;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
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

	public Integer getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}

	public LocalDate getResultPublishDate() {
		return resultPublishDate;
	}

	public void setResultPublishDate(LocalDate resultPublishDate) {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<ExamSubject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<ExamSubject> subjects) {
		this.subjects = subjects;
	}

}
