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
        accountRepository.save(accountBankEntity);
        accountBank.setUserId(id);
        return accountBank;
    }
}
