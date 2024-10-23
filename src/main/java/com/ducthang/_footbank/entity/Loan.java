package com.ducthang._footbank.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;// ID người dùng

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private LoanOffer loanOffer; // Tham chiếu đến LoanOffer

    private String status;
}