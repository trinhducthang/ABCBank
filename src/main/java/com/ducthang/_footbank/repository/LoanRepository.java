package com.ducthang._footbank.repository;

import com.ducthang._footbank.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT l FROM Loan l JOIN l.users u WHERE u.id = :userId")
    Loan findByUserId(Long userId);  // Truy vấn qua danh sách 'users'

}
