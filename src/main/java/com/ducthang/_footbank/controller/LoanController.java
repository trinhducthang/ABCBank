package com.ducthang._footbank.controller;

import com.ducthang._footbank.entity.Loan;
import com.ducthang._footbank.service.itf.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/signupLoan")
    public Loan signupLoan(@RequestParam Long userId, @RequestParam Long LoanOfferId) {
        return loanService.createLoan(userId, LoanOfferId);
    }

    @GetMapping("/api/loan/getAll")
    public List<Loan> getAllLoan() {
        return loanService.getLoans();
    }
}
