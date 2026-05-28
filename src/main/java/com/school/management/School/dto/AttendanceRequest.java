package com.school.management.School.dto;

import java.util.List;

public class AttendanceRequest {
	
	private Long schoolId;
    public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<StudentAttendance> getMarkedStudents() {
		return markedStudents;
	}

	public void setMarkedStudents(List<StudentAttendance> markedStudents) {
		this.markedStudents = markedStudents;
	}

	private String className;
	private String role;
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String date;

    // Only absent/leave students
    private List<StudentAttendance> markedStudents;

    public static class StudentAttendance {
        private Long studentId;
        private String status; // ABSENT or LEAVE
		public Long getStudentId() {
			return studentId;
		}
		public void setStudentId(Long studentId) {
			this.studentId = studentId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
    }
}
