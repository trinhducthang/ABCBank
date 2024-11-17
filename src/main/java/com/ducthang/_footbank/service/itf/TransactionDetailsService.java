package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.repository.TransactionDetailsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface TransactionDetailsService {
    public long getTransactionCountByDateAndBankNumber(LocalDate date, String bankNumber);
    public BigDecimal getTransactionSumByDateAndBankNumber(LocalDate date, String bankNumber);
    public List<TransactionDetailsRepository.BankTransactionSummary> getTransactionSummaryByBankNumber();
    public List<Map<String, Object>> getTransactionStatsByDate(LocalDate startDate, LocalDate endDate);
    public List<Object[]> getMonthlyTransactionSummary(int year, int month);
}
