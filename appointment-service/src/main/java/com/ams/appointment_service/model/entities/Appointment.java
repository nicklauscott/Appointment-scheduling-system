package com.ams.appointment_service.model.entities;

import com.ams.appointment_service.model.constant.StaffConfirmStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false) private LocalDate date;
    @Column(nullable = false) private LocalTime startTime;
    @Column(nullable = false) private LocalTime endTime;

    @Column(nullable = false) private String customerName;
    @Column(nullable = false) private String customerEmail;
    private String notes;
    @Column(nullable = false) private boolean customerCancel = false;

    @Column(nullable = false) @Enumerated(EnumType.STRING) private StaffConfirmStatus status = StaffConfirmStatus.PENDING;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "staffScheduleSnapshot_id", nullable = false)
    private StaffScheduleSnapshot staff;
}



