package com.kosign.vcrprojectsecurity.service.attendance;

import com.kosign.vcrprojectsecurity.payload.attendance.AttendanceResponse;

public interface IAttendanceService {
    void checkIn(Long userId);
    void checkOut(Long userId);

    AttendanceResponse getAttendanceInfo(Long userId);
}