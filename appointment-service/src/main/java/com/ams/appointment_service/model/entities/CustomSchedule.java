package com.ams.appointment_service.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class CustomSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "staffScheduleSnapshot_id", nullable = false)
    private StaffScheduleSnapshot staff;
}


