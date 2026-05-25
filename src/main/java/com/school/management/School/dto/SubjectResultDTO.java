package com.school.management.School.dto;

public class SubjectResultDTO {

	private String subject;

    private Double total;

    private Double obtained;

    private Double percentage;

    private String grade;

    public SubjectResultDTO(){}

    public SubjectResultDTO(
            String subject,
            Double total,
            Double obtained,
            Double percentage,
            String grade
    ) {

        this.subject=subject;
        this.total=total;
        this.obtained=obtained;
        this.percentage=percentage;
        this.grade=grade;

    }

    public String getSubject() {
        return subject;
    }

    public Double getTotal() {
        return total;
    }

    public Double getObtained() {
        return obtained;
    }

    public Double getPercentage() {
        return percentage;
    }

    public String getGrade() {
        return grade;
    }
}
