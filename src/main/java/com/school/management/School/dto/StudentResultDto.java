package com.school.management.School.dto;

import java.util.List;

public class StudentResultDto {
	private Long studentId;
    private String name;
    public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public List<SubjectMarksDto> getMarks() {
		return marks;
	}
	public void setMarks(List<SubjectMarksDto> marks) {
		this.marks = marks;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	private String rollNo;

    private List<SubjectMarksDto> marks;

    private double total;
    private double percentage;
    private String grade;
    private String result;

}
