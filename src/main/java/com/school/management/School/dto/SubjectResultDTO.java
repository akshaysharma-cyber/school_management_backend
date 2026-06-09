package com.school.management.School.dto;

import java.util.List;

public class SubjectResultDTO {

    private String subject;

    private List<ExamWiseMarksDTO> exams;

    private Double totalObtained;

    private Double totalMax;

    private Double percentage;

    private String grade;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<ExamWiseMarksDTO> getExams() {
        return exams;
    }

    public void setExams(List<ExamWiseMarksDTO> exams) {
        this.exams = exams;
    }

    public Double getTotalObtained() {
        return totalObtained;
    }

    public void setTotalObtained(Double totalObtained) {
        this.totalObtained = totalObtained;
    }

    public Double getTotalMax() {
        return totalMax;
    }

    public void setTotalMax(Double totalMax) {
        this.totalMax = totalMax;
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

}
