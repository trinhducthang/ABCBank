package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.dto.AccountBankDTO;
import org.springframework.stereotype.Service;

@Service
public interface AccountBankService {
    public AccountBankDTO createAccountBank(AccountBankDTO accountBank, long id);
}
