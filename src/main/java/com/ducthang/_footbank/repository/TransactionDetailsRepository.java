package com.ducthang._footbank.repository;

import com.ducthang._footbank.entity.TransactionDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
    @Query("SELECT COUNT(t) FROM TransactionDetails t WHERE t.transactionDate = :date AND t.bankNumber = :bankNumber")
    long countTransactionsByDateAndBankNumber(@Param("date") LocalDate date, @Param("bankNumber") String bankNumber);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransactionDetails t WHERE t.transactionDate = :date AND t.bankNumber = :bankNumber")
    BigDecimal sumTransactionsByDateAndBankNumber(@Param("date") LocalDate date, @Param("bankNumber") String bankNumber);

    @Query("SELECT t.bankNumber AS bankNumber, COUNT(t) AS transactionCount, COALESCE(SUM(t.amount), 0) AS transactionSum " +
            "FROM TransactionDetails t " +
            "GROUP BY t.bankNumber")
    List<BankTransactionSummary> getTransactionSummaryByBankNumber();

    @Query("SELECT t.transactionDate, COALESCE(SUM(t.amount), 0) " +
            "FROM TransactionDetails t " +
            "WHERE t.transactionDate BETWEEN :startDate AND :endDate " +
            "GROUP BY t.transactionDate " +
            "ORDER BY t.transactionDate")
    List<Object[]> getMonthlyTransactionSummary(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);


    interface BankTransactionSummary {
        String getBankNumber();
        long getTransactionCount();
        BigDecimal getTransactionSum();
    }
}
