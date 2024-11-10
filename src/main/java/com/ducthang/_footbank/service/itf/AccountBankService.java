package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.dto.AccountBankDTO;
import com.ducthang._footbank.entity.AccountBank;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public interface AccountBankService {
    public AccountBankDTO createAccountBank(AccountBankDTO accountBank, long id);


    public AccountBankDTO getAccountBank(long id);

    @PostAuthorize("returnObject[0].user.username == authentication.name")
    public AccountBankDTO updateAccountBank(long id, AccountBankDTO accountBank);

//    @PostAuthorize("(returnObject[0].user.username == authentication.name) || hasRole('ROLE_ADMIN')")
    public boolean deleteAccountBank(long id);

    //authorize in business
    public AccountBankDTO transferMoney(String from, String to, BigDecimal amount);

    @PostAuthorize("returnObject[0].user.username == authentication.name")
    public Set<AccountBank> findAccountBank(Long userId);

    public String getNameUser(String accountNumber);
}
