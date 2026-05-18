package com.school.management.School.service;

import java.time.LocalDate;
import com.school.management.School.entity.Attendance;

import org.springframework.stereotype.Service;

import com.school.management.School.dto.DashboardSummaryDto;
import com.school.management.School.repository.AttendanceRepository;
import com.school.management.School.repository.FeePaymentRepository;
import com.school.management.School.repository.StudentRepository;
import com.school.management.School.repository.TeacherRepository;

@Service
public class DashboardService {
	
	private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AttendanceRepository attendanceRepository;
    private final FeePaymentRepository feePaymentRepository;

    public DashboardService(
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            AttendanceRepository attendanceRepository,
            FeePaymentRepository feePaymentRepository
    ) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.attendanceRepository = attendanceRepository;
        this.feePaymentRepository = feePaymentRepository;
    }

    
    public DashboardSummaryDto getDashboardSummary(Long schoolId) {

        long totalStudents = studentRepository.countBySchoolId(schoolId);

        long totalTeachers = teacherRepository.countBySchoolId(schoolId);

        long presentToday = attendanceRepository.countBySchoolIdAndAttendanceDateAndStatus(
                schoolId,
                LocalDate.now(),
                Attendance.Status.PRESENT
        );

        Double feesCollected = feePaymentRepository.getMonthlyCollection(
                schoolId,
                LocalDate.now().getMonthValue(),
                LocalDate.now().getYear()
        );

        return new DashboardSummaryDto(
                totalStudents,
                totalTeachers,
                presentToday,
                feesCollected
        );
    }

}
