package com.ams.appointment_service.repository;

import com.ams.appointment_service.model.CustomSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomScheduleRepository extends JpaRepository<CustomSchedule, Integer> {
    Optional<CustomSchedule> findByStaffIdAndDate(UUID staffId, LocalDate date);
}
