package com.kosign.vcrprojectsecurity.service.attendance;

import com.kosign.vcrprojectsecurity.payload.attendance.AttendanceResponse;

import java.util.List;

public interface IAttendanceService {
    void checkIn(Long userId);
    void checkOut(Long userId);

    AttendanceResponse getAttendanceInfo(Long userId);

    List<AttendanceResponse> getAttendanceDetail(Long userId, Integer m);
}