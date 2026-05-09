package com.school.management.School.dto;

import java.util.List;

public class MarksEntryRequest {
	private Long schoolId;
    private Long examId;

    private String className;
    private String section;

    private Long subjectId;

    private List<StudentMarksDto> marks;

    public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
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

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public List<StudentMarksDto> getMarks() {
		return marks;
	}

	public void setMarks(List<StudentMarksDto> marks) {
		this.marks = marks;
	}

	public static class StudentMarksDto {
        public Long getStudentId() {
			return studentId;
		}
		public void setStudentId(Long studentId) {
			this.studentId = studentId;
		}
		public Double getMarksObtained() {
			return marksObtained;
		}
		public void setMarksObtained(Double marksObtained) {
			this.marksObtained = marksObtained;
		}
		private Long studentId;
        private Double marksObtained;
    }
}
