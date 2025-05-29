package com.ams.appointment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;

@Repository
public interface StaffScheduleSnapshotRepository extends JpaRepository<StaffScheduleSnapshot, UUID> {
}
