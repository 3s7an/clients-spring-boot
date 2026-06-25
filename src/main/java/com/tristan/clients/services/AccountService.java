package com.tristan.clients.services;

import com.tristan.clients.models.Account;
import com.tristan.clients.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientService clientService;



    public AccountService(AccountRepository accountRepository,  ClientService clientService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    public Account create(Account account) {
        // set balances to 0
        account.setStarterBalances();

        // set status to active
        account.setStatus("active");

        // generate iban
        account.setIban(this.generateIban(account.getClientId()));

        //generate account number
        account.setAccountNumber(this.generateAccountNumber());

        // set opened at now()
        account.setOpenedAt(LocalDateTime.now());

        return accountRepository.save(account);
    }

    // todo  - refactor to all countries
    public String generateIban(Long clientId) {
        String iban;
        do {
            var client = clientService.findById(clientId);
            var country = client.getCountry();

            String accountNumber = String.format("%010d", new Random().nextLong(1_000_000_000L));

            String bankCode = "1100";      // todo - get from config
            String branchCode = "000000";  // todo - get from config
            String bban = bankCode + branchCode + accountNumber;

            String checkDigits = calculateCheckDigits(country, bban);

            iban = country + checkDigits + bban;
        } while (this.accountRepository.existsByAccountNumber(iban));

        return iban;
    }

    private String calculateCheckDigits(String countryCode, String bban) {
        String rearranged = bban + countryCode + "00";

        StringBuilder numericString = new StringBuilder();
        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                numericString.append(Character.getNumericValue(c));
            } else {
                numericString.append(c);
            }
        }

        BigInteger number = new BigInteger(numericString.toString());
        int remainder = number.mod(BigInteger.valueOf(97)).intValue();
        int checkDigit = 98 - remainder;

        return String.format("%02d", checkDigit);
    }

    public String generateAccountNumber() {
        String accountNumber;
        do {
            String bankCode    = "1100";
            String branchCode  = String.format("%06d", new Random().nextInt(999_999));
            String accountPart = String.format("%010d", new Random().nextLong(1_000_000_000L));
            accountNumber = bankCode + "-" + branchCode + "-" + accountPart;
        } while (this.accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}
