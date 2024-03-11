package com.kosign.vcrprojectsecurity.domiain.attendance;

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
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String status;

    @Builder
    public Attendance(User user, LocalDate date, LocalTime checkIn, LocalTime checkOut, String status) {
        this.user = user;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }
}
