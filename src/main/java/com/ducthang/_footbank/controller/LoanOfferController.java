package com.ducthang._footbank.controller;

import com.ducthang._footbank.dto.response.ApiResponse;
import com.ducthang._footbank.entity.LoanOffer;
import com.ducthang._footbank.service.itf.LoanOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/loanOffer")
public class LoanOfferController {

    private final LoanOfferService loanOfferService;

    @PostMapping
    public ApiResponse<LoanOffer> create(@RequestBody LoanOffer loanOffer) {
        try {
            return ApiResponse.<LoanOffer>builder()
                    .code(HttpStatus.OK.value())
                    .message("success")
                    .result(loanOfferService.createLoanOffer(loanOffer))
                    .build();
        } catch (Exception e) {
            return ApiResponse.<LoanOffer>builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<List<LoanOffer>> getLoanOffers() {
        return ApiResponse.<List<LoanOffer>>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .result(loanOfferService.getLoanOffer())
                .build();
    }

    @GetMapping("/{offerId}")
    public ApiResponse<LoanOffer> getLoanOfferById(@PathVariable Long offerId) {
        Optional<LoanOffer> loanOffer = loanOfferService.getLoanOfferById(offerId);
        if (loanOffer.isPresent()) {
            return ApiResponse.<LoanOffer>builder()
                    .code(HttpStatus.OK.value())
                    .message("success")
                    .result(loanOffer.get())
                    .build();
        } else {
            return ApiResponse.<LoanOffer>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Loan offer not found")
                    .result(null)
                    .build();
        }
    }

    @PutMapping("/{offerId}")
    public ApiResponse<LoanOffer> updateLoanOffer(@PathVariable Long offerId, @RequestBody LoanOffer loanOffer) {
        LoanOffer updatedLoanOffer = loanOfferService.updateLoanOffer(offerId, loanOffer);
        if (updatedLoanOffer != null) {
            return ApiResponse.<LoanOffer>builder()
                    .code(HttpStatus.OK.value())
                    .message("success")
                    .result(updatedLoanOffer)
                    .build();
        } else {
            return ApiResponse.<LoanOffer>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Loan offer not found")
                    .result(null)
                    .build();
        }
    }

    @DeleteMapping("/{offerId}")
    public ApiResponse<Void> deleteLoanOffer(@PathVariable Long offerId) {
        loanOfferService.deleteLoanOffer(offerId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Loan offer deleted successfully")
                .result(null)
                .build();
    }
}
