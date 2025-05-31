package com.ams.tenant_service.model.entities.tenant;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
   name = "custom_schedule",
   uniqueConstraints = {
       @UniqueConstraint(columnNames = {"staff_id", "date"})
   }
)
@Data
public class CustomSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate date;
    @Column(name = "start_time")private LocalTime startTime;
    @Column(name = "end_time") private LocalTime endTime;
    @Column(name = "is_available") private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
}


