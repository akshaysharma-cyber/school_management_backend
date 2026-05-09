package com.school.management.School.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_results")
public class StudentResult {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_id")
    private Long schoolId;

    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "section")
    private String section;

    @Column(name = "total_marks")
    private Double totalMarks;

    @Column(name = "obtained_marks")
    private Double obtainedMarks;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "grade")
    private String grade;

    @Column(name = "result")
    private String result;
}
