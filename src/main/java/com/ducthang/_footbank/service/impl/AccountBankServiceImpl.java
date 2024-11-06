package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.dto.AccountBankDTO;
import com.ducthang._footbank.entity.AccountBank;
import com.ducthang._footbank.entity.TransactionDetails;
import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.mapper.AccountBankMapper;
import com.ducthang._footbank.repository.AccountRepository;
import com.ducthang._footbank.repository.TransactionDetailsRepository;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.AccountBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AccountBankServiceImpl implements AccountBankService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountBankMapper accountBankMapper;

    private final TransactionDetailsRepository transactionDetailsRepository;


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
        AccountBankDTO accountBankDTO = accountBankMapper.toDTO(accountBank);
        accountBankDTO.setUserId(id);
        return accountBankDTO;
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
        if(from.equals(to)) {
            throw new RuntimeException("duplicate from and to");
        }
        if (bank == null) {
            throw new RuntimeException("user source not found");
        }
        if (accountBank == null) {
            throw new RuntimeException("user destination not found");
        }
        if (bank.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("user balance is less than current balance");
        }
        accountBank.setBalance(accountBank.getBalance().add(amount));
        accountRepository.save(accountBank);
        bank.setBalance(bank.getBalance().subtract(amount));
        accountRepository.save(bank);
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setBankNumber(from);
        transactionDetails.setAmount(amount);
        transactionDetails.setTransactionDate(LocalDate.now());
        transactionDetailsRepository.save(transactionDetails);
        return accountBankMapper.toDTO(bank);
    }


}
