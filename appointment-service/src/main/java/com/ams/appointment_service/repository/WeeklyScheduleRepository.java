package com.ams.appointment_service.repository;

import com.ams.appointment_service.model.WeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.DayOfWeek;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeeklyScheduleRepository extends JpaRepository<WeeklySchedule, Integer> {
    Optional<WeeklySchedule> findByStaffIdAndDayOfWeek(UUID staffId, DayOfWeek dow);
}
