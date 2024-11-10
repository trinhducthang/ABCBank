package com.ducthang._footbank.repository;

import com.ducthang._footbank.entity.AccountBank;
import com.ducthang._footbank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<AccountBank, Long> {
    AccountBank findByAccountNumber(String accountNumber);
    Set<AccountBank> findByUserId(Long userId);
    AccountBank getUserByAccountNumber(String accountNumber);
}
