package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.entity.Loan;
import com.ducthang._footbank.repository.LoanRepository;
import com.ducthang._footbank.service.itf.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }
}
