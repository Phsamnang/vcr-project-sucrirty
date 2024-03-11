package com.kosign.vcrprojectsecurity.service.attendance;

import com.kosign.vcrprojectsecurity.common.api.StatusCode;
import com.kosign.vcrprojectsecurity.domiain.attendance.Attendance;
import com.kosign.vcrprojectsecurity.domiain.attendance.AttendanceRepository;
import com.kosign.vcrprojectsecurity.domiain.user.UserRepository;
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
    private final UserRepository userRepository;
    @Override
    public void checkIn(Long userId) {
      var user=userRepository.findById(userId).orElseThrow(()->new BusinessException(StatusCode.NOT_FOUND));
      attendanceRepository.save(
              Attendance.builder().date(LocalDate.now())
                      .checkIn(LocalTime.now())
                      .user(user)
                      .status(AttendanceStatus.PRESENT.getDescription())
                      .build()
      );
    }

    @Override
    public void checkOut(Long userId) {
        var user=userRepository.findById(userId).orElseThrow(()->new BusinessException(StatusCode.NOT_FOUND));
        var attendance=attendanceRepository.findByUserAndDate(user,LocalDate.now());
        if (attendance.isPresent()){
            attendance.get().setCheckOut(LocalTime.now());
        }
        attendanceRepository.save(attendance.get());
    }

    @Override
    public AttendanceResponse getAttendanceInfo(Long userId) {
        var user=userRepository.findById(userId).orElseThrow(()->new BusinessException(StatusCode.NOT_FOUND));
        var attendance=attendanceRepository.findByUserAndDate(user,LocalDate.now()).orElseThrow(()->new BusinessException(StatusCode.NOT_FOUND));
        return AttendanceResponse.builder()
                .checkin(attendance.getCheckIn())
                .checkOut(attendance.getCheckOut())
                .date(attendance.getDate())
                .name(attendance.getUser().getFirstName())
                .status(attendance.getStatus())
                .build();
    }
}
