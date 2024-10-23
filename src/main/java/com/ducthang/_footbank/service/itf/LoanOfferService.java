package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.entity.LoanOffer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanOfferService {
    public LoanOffer createLoanOffer(LoanOffer loanOffer);
    public List<LoanOffer> getLoanOffer();
}
