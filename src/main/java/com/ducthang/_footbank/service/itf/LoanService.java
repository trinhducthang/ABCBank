package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.entity.Loan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    public List<Loan> getLoans();
    public Loan createLoan(Long userId, Long loanOfferId);
}
