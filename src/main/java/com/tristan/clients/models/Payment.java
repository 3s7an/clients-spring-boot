package com.tristan.clients.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // account relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // transaction relationship
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", nullable = true)
    private Transaction transaction;

    private String referenceNumber;
    private String paymentType;
    private BigDecimal amount;
    private String currency;
    private String direction;
    private String counterpartyIban;
    private String counterpartyName;
    private String description;
    private String status;
    private LocalDateTime initiatedAt;
    private LocalDateTime processedAt;
    private LocalDateTime valueDate;
    private LocalDateTime updatedAt;



}
