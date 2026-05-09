package com.school.management.School.dto;

import java.util.List;



public class ResultResponse {
	
	public SummaryDto getSummary() {
		return summary;
	}
	public void setSummary(SummaryDto summary) {
		this.summary = summary;
	}
	public List<SubjectDto> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}
	public List<StudentResultDto> getStudents() {
		return students;
	}
	public void setStudents(List<StudentResultDto> students) {
		this.students = students;
	}
	private SummaryDto summary;
	private List<SubjectDto> subjects;
    private List<StudentResultDto> students;

}
