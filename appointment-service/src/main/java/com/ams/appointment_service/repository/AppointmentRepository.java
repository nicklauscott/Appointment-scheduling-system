package com.ams.appointment_service.repository;

import com.ams.appointment_service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByStaffIdAndDate(UUID staffId, LocalDate date);
}
