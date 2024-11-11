package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.entity.AccountBank;
import com.ducthang._footbank.entity.FastLoan;
import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.repository.AccountRepository;
import com.ducthang._footbank.repository.FastLoanRepository;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.FastLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FastLoanServiceImpl implements FastLoanService {
    private final FastLoanRepository fastLoanRepository;

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;


    @Override
    public FastLoan createFastLoan(Long userId,FastLoan fastLoan) {
        User user = userRepository.findById(userId).orElseThrow( () -> new RuntimeException("User not found"));
        if(user.getFastLoan() != null) throw new RuntimeException("Fast loan already exists");
        fastLoan.setUser(user);
        Set<AccountBank> bank = accountRepository.findByUserId(userId);
        if(bank.isEmpty()){
            throw new RuntimeException("User " + user.getUsername() + " not exits banks");
        }

        for(AccountBank accountBank : bank){
            accountBank.setBalance(accountBank.getBalance().add(fastLoan.getLoanAmount()));
            System.out.println(accountBank.getBalance().add(fastLoan.getLoanAmount()));
            break;
        }
        return fastLoanRepository.save(fastLoan);
    }
}
