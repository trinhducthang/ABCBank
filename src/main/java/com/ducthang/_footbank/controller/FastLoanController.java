package com.ducthang._footbank.controller;

import com.ducthang._footbank.entity.FastLoan;
import com.ducthang._footbank.service.itf.FastLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fastLoan")
@RequiredArgsConstructor
public class FastLoanController {

    private final FastLoanService fastLoanService;

    @PostMapping("/{id}")
    public FastLoan post(@PathVariable Long id, @RequestBody FastLoan fastLoan) {
        try {
            return fastLoanService.createFastLoan(id,fastLoan);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
