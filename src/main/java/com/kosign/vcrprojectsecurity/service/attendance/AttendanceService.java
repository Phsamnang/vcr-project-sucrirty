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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        var attendance = attendanceRepository.findByEmployee_IdAndDate(userId, LocalDate.now()).orElseThrow(()->new BusinessException(StatusCode.NOT_FOUND));
        attendance.setCheckOut(LocalTime.now());
        attendanceRepository.save(attendance);
    }

    @Override
    public AttendanceResponse getAttendanceInfo(Long userId) {
        var employee=employeeRepository.findById(userId).get();
        var attendance = attendanceRepository.findByEmployeeAndDate(employee,LocalDate.now());
       // var attendance = attendanceRepository.findByEmployee_IdAndDate(userId,LocalDate.now()).get();

    if (attendance==null){
        return null;
    }
        return AttendanceResponse.builder()
                .checkin(attendance.getCheckIn())
                .checkOut(attendance.getCheckOut())
                .date(attendance.getDate())
                .name(attendance.getEmployee().getName())
                .status(attendance.getStatus())
                .build();
    }

    @Override
    public List<AttendanceResponse> getAttendanceDetail(Long userId, Integer m) {
        var attendance=attendanceRepository.findByEmployee_Id(userId);

        List<AttendanceResponse> attendanceResponses=attendance.stream().filter(a->a.getDate().getYear()==LocalDate.now().getYear())
                .filter(a->a.getDate().getMonth().getValue()==m)
                .map(a->AttendanceResponse.builder()
                        .status(a.getStatus())
                        .name(a.getEmployee().getName())
                        .status(a.getStatus())
                        .date(a.getDate()).build()
                ).collect(Collectors.toList());
       // System.err.println(m);
        return attendanceResponses;
    }
}
