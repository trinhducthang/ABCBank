package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.entity.AccountBank;
import com.ducthang._footbank.entity.Loan;
import com.ducthang._footbank.entity.LoanOffer;
import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.repository.AccountRepository;
import com.ducthang._footbank.repository.LoanOfferRepository;
import com.ducthang._footbank.repository.LoanRepository;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    private final LoanOfferRepository loanOfferRepository;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan createLoan(Long userId, Long loanOfferId) {
        checkExits(userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not exits"));
        LoanOffer loanOffer = loanOfferRepository.findById(loanOfferId).orElse(null);
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setLoanOffer(loanOffer);
        loan.setStatus("Completed");
        Set<AccountBank> accountBanks = user.getAccountBanks();
        if (accountBanks != null) {
            throw new RuntimeException("Your account bank not exits");
        }
        for (AccountBank accountBank : accountBanks) {
            accountBank.setBalance(accountBank.getBalance().add(loanOffer.getLoanAmount()));
            break;
        }
        return loanRepository.save(loan);
    }

    private boolean checkExits(Long userId){
        User user = userRepository.findById(userId).get();
        if (user.getLoans() == null){
            return false;
        }
        else throw new RuntimeException("This account has existed loans");
    }


}
