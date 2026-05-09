package com.school.management.School.dto;

public class LoginResponse {


    private String message;
    public String getMessage() {
		return message;
	}

	public String getFullName() {
		return fullName;
	}

	

	public String getRole() {
		return role;
	}

	

	private String fullName;
    private String role;
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setRole(String role) {
		this.role = role;
	}



	private Long userId;
    private Long schoolId;

    public LoginResponse(String message, String fullName, String role,Long userId,Long schoolId) {
        this.message = message;
        this.fullName = fullName;
        this.role = role;
        this.userId = userId;
        this.schoolId = schoolId;
    }
}
