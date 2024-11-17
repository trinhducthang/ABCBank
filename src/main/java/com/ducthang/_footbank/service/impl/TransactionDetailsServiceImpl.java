package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.repository.TransactionDetailsRepository;
import com.ducthang._footbank.service.itf.TransactionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> getTransactionStatsByDate(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public List<Object[]> getMonthlyTransactionSummary(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return transactionDetailsRepository.getMonthlyTransactionSummary(startDate, endDate);
    }
}
