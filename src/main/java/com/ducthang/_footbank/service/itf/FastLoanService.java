package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.entity.FastLoan;
import org.springframework.stereotype.Service;

@Service
public interface FastLoanService {
    public FastLoan createFastLoan(Long userId, FastLoan fastLoan);
}
