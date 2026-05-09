package com.school.management.School.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fee_structure_items")
public class FeeStructureItem {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "fee_structure_id", nullable = false)
	    private FeeStructure feeStructure;

	    @Column(name = "school_id", nullable = false)
	    private Long schoolId;

	    @Column(name = "component_name", nullable = false)
	    private String componentName;

	    @Column(nullable = false)
	    private Double amount;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public FeeStructure getFeeStructure() {
			return feeStructure;
		}

		public void setFeeStructure(FeeStructure feeStructure) {
			this.feeStructure = feeStructure;
		}

		public Long getSchoolId() {
			return schoolId;
		}

		public void setSchoolId(Long schoolId) {
			this.schoolId = schoolId;
		}

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
}
