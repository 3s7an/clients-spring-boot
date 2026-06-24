package com.tristan.clients.mappers;

import com.tristan.clients.dtos.ClientDto;
import com.tristan.clients.models.Client;
import com.tristan.clients.requests.RegisterClientRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ClientDto toDto(Client client);
    Client toEntity(RegisterClientRequest request);
}
