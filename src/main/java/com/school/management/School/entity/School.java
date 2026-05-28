package com.school.management.School.entity;

import java.time.LocalDateTime;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "schools")
public class School {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	private String schoolName;
    private String schoolCode;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private PlanType planType = PlanType.FREE;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private LocalDateTime createdAt = LocalDateTime.now();
    
    public void generateSchoolCode() {

        if (this.schoolCode == null || this.schoolCode.isBlank()) {

            String schoolPart =
                    schoolName
                    .replaceAll("[^A-Za-z ]", "")
                    .toUpperCase();

            String[] words = schoolPart.split("\\s+");

            StringBuilder code = new StringBuilder();

            for (String w : words) {
                if (!w.isEmpty()) {
                    code.append(w.charAt(0));
                }
            }

            String cityPart =
                    city
                    .replaceAll("[^A-Za-z]", "")
                    .toUpperCase();

            if (cityPart.length() > 3) {
                cityPart =
                        cityPart.substring(0, 3);
            }

            int random =
                    100 +
                    new Random().nextInt(900);

            this.schoolCode =
                    code +
                    cityPart +
                    random;
        }
    }

}
