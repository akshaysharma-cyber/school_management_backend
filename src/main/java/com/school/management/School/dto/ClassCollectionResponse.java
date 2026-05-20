package com.school.management.School.dto;

public class ClassCollectionResponse {
	
	 private String className;

	    private Double amount;

	    public ClassCollectionResponse(
	            String className,
	            Double amount
	    ) {
	        this.className = className;
	        this.amount = amount;
	    }

	    public String getClassName() {
	        return className;
	    }

	    public Double getAmount() {
	        return amount;
	    }

}
