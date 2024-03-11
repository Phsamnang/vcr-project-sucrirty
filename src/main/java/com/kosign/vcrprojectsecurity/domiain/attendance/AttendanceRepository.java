package com.kosign.vcrprojectsecurity.domiain.attendance;

import com.kosign.vcrprojectsecurity.domiain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByUserAndDate(User user, LocalDate date);
}
