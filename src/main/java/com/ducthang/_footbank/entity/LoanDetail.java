package com.ducthang._footbank.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
public class LoanDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan; // Liên kết đến Loan

    private BigDecimal paymentAmount;
    private BigDecimal paymentAmountPaid;
    private BigDecimal outstanding;
    private LocalDate paymentDate;
    private String paymentStatus;

    // Getters and Setters
}
