package com.ducthang._footbank.controller;

import com.ducthang._footbank.dto.response.ApiResponse;
import com.ducthang._footbank.entity.LoanOffer;
import com.ducthang._footbank.service.itf.LoanOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LoanOfferController {
    private final LoanOfferService loanOfferService;

    @PostMapping("/loanOffer")
    public ApiResponse<LoanOffer> create(@RequestBody LoanOffer loanOffer) {
        try {
            return ApiResponse.<LoanOffer>builder()
                    .code(HttpStatus.OK.value())
                    .message("success")
                    .result(loanOfferService.createLoanOffer(loanOffer))
                    .build();
        }
        catch (Exception e) {
            return ApiResponse.<LoanOffer>builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @GetMapping("/loanOffer")
    public ApiResponse<List<LoanOffer>> getLoanOffers(){
        return ApiResponse.<List<LoanOffer>>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .result(loanOfferService.getLoanOffer())
                .build();
    }

}
