package com.ams.auth_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(
  name = "refresh_token",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tenant_id", "email"})
  }
)
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "password_hash")
    private String hashedToken;

    @Column(nullable = false, name = "tenant_id")
    private String tenantId;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @Column(name = "created_at")
    private Instant createdAt;
}
