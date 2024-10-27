package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.repository.TransactionDetailsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public interface TransactionDetailsService {
    public long getTransactionCountByDateAndBankNumber(LocalDate date, String bankNumber);
    public BigDecimal getTransactionSumByDateAndBankNumber(LocalDate date, String bankNumber);
    public List<TransactionDetailsRepository.BankTransactionSummary> getTransactionSummaryByBankNumber();
}
