package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.dto.AccountBankDTO;
import com.ducthang._footbank.entity.AccountBank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public interface AccountBankService {
    public AccountBankDTO createAccountBank(AccountBankDTO accountBank, long id);

    public AccountBankDTO getAccountBank(long id);

    public AccountBankDTO updateAccountBank(long id, AccountBankDTO accountBank);

    public boolean deleteAccountBank(long id);

    public AccountBankDTO transferMoney(String from, String to, BigDecimal amount);


    public Set<AccountBank> findAccountBank(Long userId);
}
