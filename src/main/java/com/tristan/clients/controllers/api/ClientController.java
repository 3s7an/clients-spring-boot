package com.tristan.clients.controllers.api;

import com.tristan.clients.dtos.ClientDto;
import com.tristan.clients.mappers.UserMapper;
import com.tristan.clients.models.Client;
import com.tristan.clients.requests.RegisterClientRequest;
import com.tristan.clients.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final UserMapper userMapper;


    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients(@RequestParam String sort) {
        return ResponseEntity.ok(
                clientService.findAll(sort).stream().map(userMapper::toDto).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        var client = clientService.findById(id);

        if(client == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(client));
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(
            @RequestBody RegisterClientRequest request,
            UriComponentsBuilder uriBuilder
    ){
        System.out.println(request);
        var client =  userMapper.toEntity(request);
        this.clientService.create(client);

        var clientDto = userMapper.toDto(client);

        // redirect
        var uri = uriBuilder.path("/clients/{id}").buildAndExpand(clientDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clientDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(
            @PathVariable Long id,
            @RequestBody RegisterClientRequest request
    ){
        var clientDto = clientService.findById(id);

        if(clientDto == null){
            return ResponseEntity.notFound().build();
        }

        var client_data = userMapper.toEntity(request);
        client_data.setId(clientDto.getId());

        clientService.update(client_data);

        return ResponseEntity.ok(userMapper.toDto(client_data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        var client = clientService.findById(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }

        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
