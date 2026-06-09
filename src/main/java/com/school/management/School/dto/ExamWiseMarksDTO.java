package com.school.management.School.dto;

public class ExamWiseMarksDTO {
	 private String examType;

	    private Double obtained;

	    private Double maxMarks;

	    public ExamWiseMarksDTO() {
	    }

	    public ExamWiseMarksDTO(
	            String examType,
	            Double obtained,
	            Double maxMarks) {

	        this.examType = examType;
	        this.obtained = obtained;
	        this.maxMarks = maxMarks;
	    }

	    public String getExamType() {
	        return examType;
	    }

	    public void setExamType(String examType) {
	        this.examType = examType;
	    }

	    public Double getObtained() {
	        return obtained;
	    }

	    public void setObtained(Double obtained) {
	        this.obtained = obtained;
	    }

	    public Double getMaxMarks() {
	        return maxMarks;
	    }

	    public void setMaxMarks(Double maxMarks) {
	        this.maxMarks = maxMarks;
	    }
}
