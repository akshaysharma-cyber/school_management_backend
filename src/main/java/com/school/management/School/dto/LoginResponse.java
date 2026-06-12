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
    private String token;
    private String refreshToken;
    private String schoolName;

    public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public LoginResponse(String message, String fullName, String role,Long userId,Long schoolId,String token,String refreshToken,String schoolName) {
        this.message = message;
        this.fullName = fullName;
        this.role = role;
        this.userId = userId;
        this.schoolId = schoolId;
        this.token=token;
        this.refreshToken=refreshToken;
        this.schoolName= schoolName;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
