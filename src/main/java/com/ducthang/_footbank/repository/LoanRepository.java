package com.ducthang._footbank.repository;

import com.ducthang._footbank.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
