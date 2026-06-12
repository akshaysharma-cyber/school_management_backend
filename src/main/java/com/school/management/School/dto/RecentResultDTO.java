package com.school.management.School.dto;

public class RecentResultDTO {
	
	public RecentResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	private String examType;
    private String className;

    private Long students;

    private Double average;

    private String topperName;

    private Double topperPercentage;

    private String publishedOn;

    

    public RecentResultDTO(
            String examType,
            String className,
            Long students,
            Double average,
            String topperName,
            Double topperPercentage,
            String publishedOn
            
    ) {
        this.examType = examType;
        this.className = className;
        this.students = students;
        this.average = average;
        this.topperName = topperName;
        this.topperPercentage = topperPercentage;
        this.publishedOn = publishedOn;
        
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



	public Long getStudents() {
		return students;
	}



	public void setStudents(Long students) {
		this.students = students;
	}



	public Double getAverage() {
		return average;
	}



	public void setAverage(Double average) {
		this.average = average;
	}



	public String getTopperName() {
		return topperName;
	}



	public void setTopperName(String topperName) {
		this.topperName = topperName;
	}



	public Double getTopperPercentage() {
		return topperPercentage;
	}



	public void setTopperPercentage(Double topperPercentage) {
		this.topperPercentage = topperPercentage;
	}



	public String getPublishedOn() {
		return publishedOn;
	}



	public void setPublishedOn(String publishedOn) {
		this.publishedOn = publishedOn;
	}

    
    

}
