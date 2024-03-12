package com.kosign.vcrprojectsecurity.service.attendance;

import com.kosign.vcrprojectsecurity.common.api.StatusCode;
import com.kosign.vcrprojectsecurity.domiain.attendance.Attendance;
import com.kosign.vcrprojectsecurity.domiain.attendance.AttendanceRepository;
import com.kosign.vcrprojectsecurity.domiain.employee.EmployeeRepository;
import com.kosign.vcrprojectsecurity.enums.AttendanceStatus;
import com.kosign.vcrprojectsecurity.exception.BusinessException;
import com.kosign.vcrprojectsecurity.payload.attendance.AttendanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AttendanceService implements IAttendanceService{
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public void checkIn(Long userId) {
        var employee = employeeRepository.findById(userId).orElseThrow(() -> new BusinessException(StatusCode.NOT_FOUND));
      attendanceRepository.save(
              Attendance.builder().date(LocalDate.now())
                      .checkIn(LocalTime.now())
                      .employee(employee)
                      .status(AttendanceStatus.PRESENT.getDescription())
                      .build()
      );
    }

    @Override
    public void checkOut(Long userId) {
        var attendance = attendanceRepository.findByEmployee_IdAndDate(userId, LocalDate.now());
        attendance.setCheckOut(LocalTime.now());
        attendanceRepository.save(attendance);
    }

    @Override
    public AttendanceResponse getAttendanceInfo(Long userId) {
        var attendance = attendanceRepository.findByEmployeeId(userId);
        return AttendanceResponse.builder()
                .checkin(attendance.getCheckIn())
                .checkOut(attendance.getCheckOut())
                .date(attendance.getDate())
                .name(attendance.getEmployee().getName())
                .status(attendance.getStatus())
                .build();
    }
}
