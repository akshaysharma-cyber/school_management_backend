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

    public LoginResponse(String message, String fullName, String role) {
        this.message = message;
        this.fullName = fullName;
        this.role = role;
    }
}
