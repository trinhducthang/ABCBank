package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.dto.AccountBankDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountBankService {
    public AccountBankDTO createAccountBank(AccountBankDTO accountBank, long id);

    public AccountBankDTO getAccountBank(long id);

    public AccountBankDTO updateAccountBank(long id, AccountBankDTO accountBank);

    public boolean deleteAccountBank(long id);

    public AccountBankDTO transferMoney(String from, String to, BigDecimal amount);
}
