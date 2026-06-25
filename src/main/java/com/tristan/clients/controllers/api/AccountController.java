package com.tristan.clients.controllers.api;

import com.tristan.clients.dtos.AccountDto;
import com.tristan.clients.mappers.AccountMapper;
import com.tristan.clients.requests.CreateAccountRequest;
import com.tristan.clients.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/acount")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public String createAccount(
            @RequestBody CreateAccountRequest request
    ) {
        var account = this.accountMapper.toEntity(request);
        this.accountService.create(account);

        return "ok";
    }
}
