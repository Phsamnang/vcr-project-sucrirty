package com.kosign.vcrprojectsecurity.payload.attendance;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record AttendanceResponse(String name, LocalDate date, LocalTime checkin, LocalTime checkOut, String status) {
}
