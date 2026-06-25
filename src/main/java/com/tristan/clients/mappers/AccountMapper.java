package com.tristan.clients.mappers;

import com.tristan.clients.dtos.AccountDto;
import com.tristan.clients.models.Account;
import com.tristan.clients.requests.CreateAccountRequest;
import com.tristan.clients.requests.RegisterClientRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);
    Account toEntity(CreateAccountRequest request);
}
