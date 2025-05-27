package com.ams.appointment_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private String clientName;
    private String clientEmail;
    private String notes;

    @Enumerated(EnumType.STRING) private AppointmentStaus staus;
    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "staffScheduleSnapshot_id", nullable = false)
    private StaffScheduleSnapshot staff;
}

enum AppointmentStaus {
    CANCELLED, ACTIVE, COMPLETED, POSTPONED
}

