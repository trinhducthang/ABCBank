package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.entity.LoanDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanDetailService {
    public List<LoanDetail> findAll();
    public LoanDetail createLoanDetail(LoanDetail loanDetail);
}
