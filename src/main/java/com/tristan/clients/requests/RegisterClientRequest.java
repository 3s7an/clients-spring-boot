package com.tristan.clients.requests;

import lombok.Data;

@Data
public class RegisterClientRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
