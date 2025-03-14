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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

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
        AccountBank accountBank = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("bank not found"));
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
    public AccountBankDTO transferMoney(String from, String to,String description, BigDecimal amount) {


        AccountBank bank = accountRepository.findByAccountNumber(from);
        if (bank == null) {
            throw new RuntimeException("user source not found");
        }
        AccountBank accountBank = accountRepository.findByAccountNumber(to);
        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        String username = bank.getUser().getUsername();
        if(!authenticationName.equals(username)) throw new RuntimeException("Invalid authentication");
        if(from.equals(to)) {
            throw new RuntimeException("duplicate from and to");
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
        if(description.isEmpty()){
            transactionDetails.setDescription(bank.getUser().getFirstName() +  " " + bank.getUser().getLastName() + " chuyen tien.");
        }
        else{
            transactionDetails.setDescription(description);
        }
        transactionDetails.setTransactionDate(LocalDate.now());
        transactionDetailsRepository.save(transactionDetails);
        return accountBankMapper.toDTO(bank);
    }


    @Override
    public Set<AccountBank> findAccountBank(Long userId){
        Set<AccountBank> accountBank = accountRepository.findByUserId(userId);
        if(accountBank.isEmpty()) return null;
        String authenticationName = SecurityContextHolder.getContext().getAuthentication().getName();
        for(AccountBank accountBankEntity : accountBank){
            String username = accountBankEntity.getUser().getUsername();
            if(!authenticationName.equals(username)) throw new RuntimeException("Invalid authentication");
        }
        return accountRepository.findByUserId(userId);
    }

    @Override
    public String getNameUser(String accountNumber) {
        AccountBank accountBank = accountRepository.findByAccountNumber(accountNumber);
        if(accountBank == null) throw new RuntimeException("User not found please re-enter!");
        User user = accountBank.getUser();
        return user.getLastName() + " " + user.getFirstName() ;
    }



}
