package com.ams.auth_service.repository;

import com.ams.auth_service.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmailAndTenantId(String email, String tenantId);
    void deleteByEmailAndTenantId(String email, String tenantId);
}
