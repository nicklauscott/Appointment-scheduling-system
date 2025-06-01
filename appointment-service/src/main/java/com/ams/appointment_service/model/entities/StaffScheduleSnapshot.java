package com.ams.appointment_service.model.entities;

import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "staff_schedule_snapshot")
@ToString
public class StaffScheduleSnapshot { 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column private String email;
    @Column private String name;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeeklySchedule> weeklySchedule;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomSchedule> customSchedules;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;
}


