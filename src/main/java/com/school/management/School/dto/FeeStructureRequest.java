package com.school.management.School.dto;

import java.util.List;

public class FeeStructureRequest {
	
	 private Long schoolId;
	    private String className;
	    private String academicYear;
	    private String frequency;

	    private List<FeeItem> items;

	    public static class FeeItem {
	        private String componentName;
	        public String getComponentName() {
				return componentName;
			}
			public void setComponentName(String componentName) {
				this.componentName = componentName;
			}
			public Double getAmount() {
				return amount;
			}
			public void setAmount(Double amount) {
				this.amount = amount;
			}
			private Double amount;
	    }

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

		public String getAcademicYear() {
			return academicYear;
		}

		public void setAcademicYear(String academicYear) {
			this.academicYear = academicYear;
		}

		public String getFrequency() {
			return frequency;
		}

		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}

		public List<FeeItem> getItems() {
			return items;
		}

		public void setItems(List<FeeItem> items) {
			this.items = items;
		}

}
