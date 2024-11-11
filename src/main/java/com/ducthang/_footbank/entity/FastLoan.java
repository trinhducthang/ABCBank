package com.ducthang._footbank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table
@Data
public class FastLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal loanAmount;
    private String duration;
    private BigDecimal payMonthly;
    private BigDecimal interestAndOther;

    @OneToOne(mappedBy = "fastLoan") // `loan` là trường trong `User` sở hữu mối quan hệ
    private User user;
}
