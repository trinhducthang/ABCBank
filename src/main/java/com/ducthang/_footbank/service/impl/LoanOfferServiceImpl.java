package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.entity.LoanOffer;
import com.ducthang._footbank.repository.LoanOfferRepository;
import com.ducthang._footbank.service.itf.LoanOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanOfferServiceImpl implements LoanOfferService {

    private final LoanOfferRepository loanOfferRepository;

    @Override
    public LoanOffer createLoanOffer(LoanOffer loanOffer) {
        return loanOfferRepository.save(loanOffer);
    }

    @Override
    public List<LoanOffer> getLoanOffer() {
        return loanOfferRepository.findAll();
    }

    @Override
    public Optional<LoanOffer> getLoanOfferById(Long offerId) {
        return loanOfferRepository.findById(offerId);
    }

    @Override
    public LoanOffer updateLoanOffer(Long offerId, LoanOffer loanOffer) {
        if (loanOfferRepository.existsById(offerId)) {
            loanOffer.setOfferId(offerId);
            return loanOfferRepository.save(loanOffer);
        }
        return null;
    }

    @Override
    public void deleteLoanOffer(Long offerId) {
        loanOfferRepository.deleteById(offerId);
    }
}
