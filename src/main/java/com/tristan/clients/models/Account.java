package com.tristan.clients.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // client relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)

    private Client client;
    private String iban;
    private String accountNumber;
    private String accountType;
    private String currency;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private BigDecimal reservedBalance;
    private String status;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;

    public void setStarterBalances(){
        this.balance = BigDecimal.ZERO;
        this.availableBalance = BigDecimal.ZERO;
        this.reservedBalance = BigDecimal.ZERO;
    }

    public Long getClientId(){
        return client != null ? client.getId() : null;
    }
}
