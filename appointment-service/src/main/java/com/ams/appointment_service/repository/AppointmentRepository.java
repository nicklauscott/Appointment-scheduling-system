package com.ams.appointment_service.repository;

import com.ams.appointment_service.model.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByStaffIdAndDate(UUID staffId, LocalDate date);
    Optional<Appointment> findByIdAndCustomerEmail(long id, String email);

    @Modifying
    @Query("DELETE FROM Appointment a WHERE a.id = :id AND a.staff.id = :staffId")
    int deleteByIdAndStaffId(long id, UUID staffId);
}
