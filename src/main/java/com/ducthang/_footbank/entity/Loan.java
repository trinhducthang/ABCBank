package com.ducthang._footbank.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;// ID người dùng

    @OneToMany(mappedBy = "loan")
    @JsonManagedReference
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private LoanOffer loanOffer; // Tham chiếu đến LoanOffer

    private String status;


}