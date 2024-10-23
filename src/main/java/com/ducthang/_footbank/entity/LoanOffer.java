package com.ducthang._footbank.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
public class LoanOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    private BigDecimal loanAmount;
    private BigDecimal interestRate;
    private String duration; // Thời gian vay (ví dụ: 12 tháng)

    // Getters and Setters
}
