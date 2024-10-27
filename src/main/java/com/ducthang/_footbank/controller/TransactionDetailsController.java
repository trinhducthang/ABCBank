package com.ducthang._footbank.controller;

import com.ducthang._footbank.dto.response.ApiResponse;
import com.ducthang._footbank.repository.TransactionDetailsRepository;
import com.ducthang._footbank.service.itf.TransactionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TransactionDetailsController {

    private final TransactionDetailsService transactionDetailsService;

    @GetMapping("/transactions/stats")
    public ApiResponse<Map<String, Object>> getTransactionStatsByDateAndBankNumber(
            @RequestParam("date") String date,
            @RequestParam("bankNumber") String bankNumber) {

        LocalDate transactionDate = LocalDate.parse(date);
        long transactionCount = transactionDetailsService.getTransactionCountByDateAndBankNumber(transactionDate, bankNumber);
        BigDecimal transactionSum = transactionDetailsService.getTransactionSumByDateAndBankNumber(transactionDate, bankNumber);

        Map<String, Object> result = new HashMap<>();
        result.put("transactionCount", transactionCount);
        result.put("transactionSum", transactionSum);

        return ApiResponse.<Map<String, Object>>builder()
                .code(HttpStatus.OK.value())
                .message("Get transaction statistics success!")
                .result(result)
                .build();
    }


    @GetMapping("/bank-summary")
    public ApiResponse<List<TransactionDetailsRepository.BankTransactionSummary>> getBankTransactionSummary() {
        List<TransactionDetailsRepository.BankTransactionSummary> summary = transactionDetailsService.getTransactionSummaryByBankNumber();
        return ApiResponse.<List<TransactionDetailsRepository.BankTransactionSummary>>builder()
                .code(HttpStatus.OK.value())
                .message("Get bank transaction summary success!")
                .result(summary)
                .build();
    }
}
