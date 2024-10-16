package com.ducthang._footbank.repository;

import com.ducthang._footbank.entity.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountBank, Long> {
    AccountBank findByAccountNumber(String accountNumber);
}
