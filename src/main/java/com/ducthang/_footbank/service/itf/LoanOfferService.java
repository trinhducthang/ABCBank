package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.entity.LoanOffer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LoanOfferService {
    LoanOffer createLoanOffer(LoanOffer loanOffer);
    List<LoanOffer> getLoanOffer();
    Optional<LoanOffer> getLoanOfferById(Long offerId);
    LoanOffer updateLoanOffer(Long offerId, LoanOffer loanOffer);
    void deleteLoanOffer(Long offerId);
}
