package com.ams.tenant_service.repository;

import com.ams.tenant_service.model.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
}
