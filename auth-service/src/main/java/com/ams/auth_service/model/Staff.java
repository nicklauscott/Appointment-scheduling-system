package com.ams.auth_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
   name = "staff",
   uniqueConstraints = {
     @UniqueConstraint(columnNames = {"tenant_id", "email"})
   }
)
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "password_hash")
    private String hashedPassword;

    @Column(nullable = false)
    private String role = "USER";

    @Column(nullable = false, name = "tenant_id")
    private String tenantId;
}
