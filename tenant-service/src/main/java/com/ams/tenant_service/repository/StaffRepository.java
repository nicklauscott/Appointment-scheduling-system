package com.ams.tenant_service.repository;

import com.ams.tenant_service.model.entities.tenant.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID> {
}
