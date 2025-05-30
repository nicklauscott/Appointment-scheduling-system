package com.ams.appointment_service.model.entities;

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
    @Column(name = "payment_provider") private String paymentProvider;
    @Column(name = "external_payment_id") private String externalPaymentId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "created_at") private Instant createdAt;
}

enum PaymentStatus{
    PENDING, PAID, FAILED
}