package com.ams.appointment_service.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class StaffScheduleSnapshot { 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeeklySchedule> weeklySchedule;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomSchedule> customSchedules;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;
}
