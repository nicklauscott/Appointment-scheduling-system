package com.ams.appointment_service.model.entities;

import com.ams.appointment_service.model.constant.AppointmentStatus;
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
    @Column(nullable = false, name = "start_time") private LocalTime startTime;
    @Column(nullable = false, name = "end_time") private LocalTime endTime;

    @Column(nullable = false, name = "customer_name") private String customerName;
    @Column(nullable = false, name = "customer_email") private String customerEmail;
    private String notes;
    @Column(nullable = false, name = "customer_status")
    @Enumerated(EnumType.STRING) private AppointmentStatus customerStatus = AppointmentStatus.APPROVED;;

    @Column(nullable = false, name = "staff_status") @Enumerated(EnumType.STRING) private AppointmentStatus staffStatus = AppointmentStatus.PENDING;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "staff_schedule_snapshot_id", nullable = false)
    private StaffScheduleSnapshot staff;
}



