package com.tristan.clients.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transactions")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // account relationship
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // payment relationship
    @OneToOne(mappedBy = "transaction")
    private Payment payment;

    private BigDecimal amount;
    private String currency;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private String type;
    private String description;
    private LocalDateTime bookedAt;
    private LocalDateTime valueDate;



}
