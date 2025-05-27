package com.ams.appointment_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    private BigDecimal amount;
    private String currency;
    private String paymentProvider;
    private String externalPaymentId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private Instant createdAt;
}

enum PaymentStatus{
    PENDING, PAID, FAILED
}