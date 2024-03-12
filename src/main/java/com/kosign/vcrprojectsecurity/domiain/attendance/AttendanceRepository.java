package com.kosign.vcrprojectsecurity.domiain.attendance;

import com.kosign.vcrprojectsecurity.domiain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    @Query("select a from Attendance a where a.employee.id = ?1 and a.date = ?2")
    Attendance findByEmployee_IdAndDate(Long id, LocalDate date);

    @Query("select a from Attendance a where a.employee.id = ?1")
    Attendance findByEmployeeId(Long id);
}
