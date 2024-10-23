package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.entity.LoanDetail;
import com.ducthang._footbank.repository.LoanDetailRepository;
import com.ducthang._footbank.service.itf.LoanDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanDetailServiceImpl implements LoanDetailService {

    private final LoanDetailRepository loanDetailRepository;

    @Override
    public List<LoanDetail> findAll() {
        return loanDetailRepository.findAll();
    }

    @Override
    public LoanDetail createLoanDetail(LoanDetail loanDetail) {
        return loanDetailRepository.save(loanDetail);
    }
}
