package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.repository.TransactionDetailsRepository;
import com.ducthang._footbank.service.itf.TransactionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    private final TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public long getTransactionCountByDateAndBankNumber(LocalDate date, String bankNumber) {
        return transactionDetailsRepository.countTransactionsByDateAndBankNumber(date, bankNumber);
    }

    @Override
    public BigDecimal getTransactionSumByDateAndBankNumber(LocalDate date, String bankNumber) {
        return transactionDetailsRepository.sumTransactionsByDateAndBankNumber(date, bankNumber);
    }

    public List<TransactionDetailsRepository.BankTransactionSummary> getTransactionSummaryByBankNumber() {
        return transactionDetailsRepository.getTransactionSummaryByBankNumber();
    }
}
