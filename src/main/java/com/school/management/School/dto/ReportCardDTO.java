package com.school.management.School.dto;

import java.util.List;

public class ReportCardDTO {
	
	private String studentName;
    private String className;
    private Long rollNumber;

    private List<SubjectResultDTO> subjects;

    private Double total;
    private Double percentage;
    private Double obtained;
    private String schoolName;
    private String district;
    private String state;
    private String academicYear;

    public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Double getObtained() {
		return obtained;
	}

	public void setObtained(Double obtained) {
		this.obtained = obtained;
	}

	private String grade;

    private Integer rank;

    public ReportCardDTO(){}

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(Long rollNumber) {
        this.rollNumber = rollNumber;
    }

    public List<SubjectResultDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectResultDTO> subjects) {
        this.subjects = subjects;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
