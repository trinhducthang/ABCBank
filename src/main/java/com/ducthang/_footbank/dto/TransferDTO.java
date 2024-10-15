package com.ducthang._footbank.dto;

import jakarta.persistence.*;

@Entity
@Table
public class TransferDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
