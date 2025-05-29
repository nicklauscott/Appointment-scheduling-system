package com.ams.appointment_service.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
public class WeeklySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isWorkingDay;

    @ManyToOne
    @JoinColumn(name = "staffScheduleSnapshot_id", nullable = false)
    private StaffScheduleSnapshot staff;
}


