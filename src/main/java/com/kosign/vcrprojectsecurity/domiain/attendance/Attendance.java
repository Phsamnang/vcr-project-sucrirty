package com.kosign.vcrprojectsecurity.domiain.attendance;

import com.kosign.vcrprojectsecurity.domiain.employee.Employee;
import com.kosign.vcrprojectsecurity.domiain.user.User;
import com.kosign.vcrprojectsecurity.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String status;

    @Builder
    public Attendance(Employee employee, LocalDate date, LocalTime checkIn, LocalTime checkOut, String status) {
        this.employee = employee;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }
}
