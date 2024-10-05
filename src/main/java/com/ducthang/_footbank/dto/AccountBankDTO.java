package com.ducthang._footbank.dto;

import com.ducthang._footbank.entity.enum_.AccountName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountBankDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Account bank name cannot be null")
    private AccountName accountName;

    @NotBlank(message = "Account number cannot be blank")
    @Size(max = 20, message = "Account number cannot exceed 20 characters")
    private String accountNumber;

    @NotNull(message = "Balance cannot be null")
    private BigDecimal balance;
}
