package com.ducthang._footbank.controller;

import com.ducthang._footbank.dto.AccountBankDTO;
import com.ducthang._footbank.dto.TransferDTO;
import com.ducthang._footbank.dto.response.ApiResponse;
import com.ducthang._footbank.entity.AccountBank;
import com.ducthang._footbank.service.itf.AccountBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class AccountBankController {
    private final AccountBankService accountBankService;

    @PostMapping("/create")
    public ApiResponse<AccountBankDTO> createAccount(@RequestBody AccountBankDTO accountBank, @RequestParam long id) {
        try {
            return ApiResponse.<AccountBankDTO>builder()
                    .code(HttpStatus.OK.value())
                    .message("create bank successfully")
                    .result(accountBankService.createAccountBank(accountBank,id))
                    .build();
        }
        catch (Exception e) {
            return ApiResponse.<AccountBankDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .result(null)
                    .message(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/info")
    public ApiResponse<AccountBankDTO> info(@RequestParam long id) {
        try {
            return ApiResponse.<AccountBankDTO>builder()
                    .code(HttpStatus.OK.value())
                    .message("Get success")
                    .result(accountBankService.getAccountBank(id))
                    .build();
        }
        catch (Exception e) {
            return ApiResponse.<AccountBankDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping("/transfer")
    public ApiResponse<AccountBankDTO> transfer(@RequestBody TransferDTO transferDTO) {
        try {
            return ApiResponse.<AccountBankDTO>builder()
                    .code(HttpStatus.OK.value())
                    .message("Transfer success")
                    .result(accountBankService.transferMoney(transferDTO.from,transferDTO.to,transferDTO.amount))
                    .build();
        }
        catch (Exception e) {
            return ApiResponse.<AccountBankDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
        }
    }


    @GetMapping("/info/{id}")
    public Set<AccountBank> findAccountBank(@PathVariable long id) {
        return accountBankService.findAccountBank(id);
    }
}
