package com.tristan.clients.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;
    private String iban;
    private String accountNumber;
    private String accountType;
    private String currency;
    private Double balance;
    private Double availableBalance;
    private Double reservedBalance;
    private String status;
    private String openedAt;
    private String closedAt;
}
