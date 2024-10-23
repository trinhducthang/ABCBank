package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.dto.AccountBankDTO;
import com.ducthang._footbank.entity.AccountBank;
import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.mapper.AccountBankMapper;
import com.ducthang._footbank.repository.AccountRepository;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.AccountBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountBankServiceImpl implements AccountBankService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountBankMapper accountBankMapper;


    @Override
    public AccountBankDTO createAccountBank(AccountBankDTO accountBank, long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        AccountBank accountBankEntity = accountBankMapper.toEntity(accountBank);
        accountBankEntity.setUser(user);
        accountBankEntity.setBalance(BigDecimal.valueOf(00.00));
        accountRepository.save(accountBankEntity);
        accountBank.setUserId(id);
        return accountBank;
    }

    @Override
    public AccountBankDTO getAccountBank(long id) {
        AccountBank accountBank = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        return accountBankMapper.toDTO(accountBank);
    }

    @Override
    public AccountBankDTO updateAccountBank(long id, AccountBankDTO accountBank) {
        AccountBank accountBankEntity = accountBankMapper.toEntity(accountBank);
        accountBankEntity.setId(id);
        accountRepository.save(accountBankEntity);
        accountBank.setUserId(id);
        return accountBank;
    }

    @Override
    public boolean deleteAccountBank(long id) {
        AccountBank accountBank = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        accountRepository.delete(accountBank);
        return true;
    }

    @Override
    public AccountBankDTO transferMoney(String from, String to, BigDecimal amount) {
        AccountBank bank = accountRepository.findByAccountNumber(from);
        AccountBank accountBank = accountRepository.findByAccountNumber(to);
        if (bank == null) {
            throw new RuntimeException("user not found");
        }
        if (accountBank == null) {
            throw new RuntimeException("user destination not found");
        }
        accountBank.setBalance(accountBank.getBalance().add(amount));
        accountRepository.save(accountBank);
        bank.setBalance(accountBank.getBalance().subtract(amount));
        accountRepository.save(bank);
        return accountBankMapper.toDTO(bank);
    }


}
