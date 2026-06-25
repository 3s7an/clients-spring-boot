package com.tristan.clients.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private Long clientId;
    private String accountType;
    private String currency;
}