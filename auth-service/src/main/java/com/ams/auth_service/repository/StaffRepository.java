package com.ams.auth_service.repository;

import com.ams.auth_service.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByEmailAndTenantId(String email, String tenantId);
}
