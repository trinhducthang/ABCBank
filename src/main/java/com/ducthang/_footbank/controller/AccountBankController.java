package com.ducthang._footbank.controller;

import com.ducthang._footbank.dto.AccountBankDTO;
import com.ducthang._footbank.dto.TransferDTO;
import com.ducthang._footbank.dto.response.ApiResponse;
import com.ducthang._footbank.dto.response.ResponseData;
import com.ducthang._footbank.service.itf.AccountBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
                    .result(accountBankService.transferMoney(transferDTO.from,transferDTO.to,transferDTO.description,transferDTO.amount))
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
    public ResponseData<?> findAccountBank(@PathVariable long id) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),"get success", LocalDateTime.now(),accountBankService.findAccountBank(id));
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/getUser/{accountNumber}")
    public String findAccountBank(@PathVariable String accountNumber) {
        try {
            return accountBankService.getNameUser(accountNumber);
        }
        catch (RuntimeException e){
            return e.getMessage();
        }
    }
}
