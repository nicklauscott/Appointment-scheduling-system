package com.ams.appointment_service.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "custom_schedule")
public class CustomSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate date;
    @Column(name = "start_time")private LocalTime startTime;
    @Column(name = "end_time") private LocalTime endTime;
    @Column(name = "is_available") private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "staff_schedule_snapshot_id", nullable = false)
    private StaffScheduleSnapshot staff;
}

/*

private LocalDate date;

@ManyToOne
    @JoinColumn(name = "staff_schedule_snapshot_id", nullable = false)
    private StaffScheduleSnapshot staff;


    "i need date to be unique based on the staff id"

    ALTER TABLE custom_schedule ADD CONSTRAINT unique_date_per_staff UNIQUE (date, staff_schedule_snapshot_id);

 */


