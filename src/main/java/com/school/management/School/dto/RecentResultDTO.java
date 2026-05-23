package com.school.management.School.dto;

public class RecentResultDTO {
	
	private String examName;
    private String className;

    private Long students;

    private Double average;

    private String topperName;

    private Double topperPercentage;

    private String publishedOn;

    private Boolean smsSent;

    public RecentResultDTO(
            String examName,
            String className,
            Long students,
            Double average,
            String topperName,
            Double topperPercentage,
            String publishedOn,
            Boolean smsSent
    ) {
        this.examName = examName;
        this.className = className;
        this.students = students;
        this.average = average;
        this.topperName = topperName;
        this.topperPercentage = topperPercentage;
        this.publishedOn = publishedOn;
        this.smsSent = smsSent;
    }

    public String getExamName() { return examName; }
    public String getClassName() { return className; }
    public Long getStudents() { return students; }
    public Double getAverage() { return average; }
    public String getTopperName() { return topperName; }
    public Double getTopperPercentage() { return topperPercentage; }
    public String getPublishedOn() { return publishedOn; }
    public Boolean getSmsSent() { return smsSent; }

}
