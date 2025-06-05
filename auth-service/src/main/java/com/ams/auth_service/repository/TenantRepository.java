package com.ams.auth_service.repository;

import com.ams.auth_service.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
    Optional<Tenant> findByIdAndEmail(String tenantId, String email);
}
