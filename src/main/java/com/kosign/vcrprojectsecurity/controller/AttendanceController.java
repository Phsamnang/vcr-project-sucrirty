package com.kosign.vcrprojectsecurity.controller;

import com.kosign.vcrprojectsecurity.service.attendance.IAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class AttendanceController extends VCRRestController {
    private final IAttendanceService service;
    @PostMapping("/checkin/{userId}")
    public ResponseEntity<?> checkIn(@PathVariable("userId") Long userId) {

        service.checkIn(userId);
        return ok();
    }
    @PostMapping("/checkout/{userId}")
    public ResponseEntity<?> checkOut(@PathVariable("userId") Long userId) {
        service.checkOut(userId);
        return ok();
    }
    @GetMapping("/info/{userId}")
    public ResponseEntity<?> getInfo(@PathVariable("userId") Long userId) {
        return ok(service.getAttendanceInfo(userId));
    }
}
