package com.tristan.clients.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class ClientDto {
    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime createdAt;

}
