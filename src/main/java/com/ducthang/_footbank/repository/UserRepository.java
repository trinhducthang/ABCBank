package com.ducthang._footbank.repository;

import com.ducthang._footbank.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Page<User> findAll(Pageable pageable);

    // Truy vấn lấy số lượng người dùng đăng ký theo ngày
    @Query("SELECT COUNT(u) FROM User u WHERE u.createdAt >= :startDate AND u.createdAt < :endDate")
    Long countUsersByCreatedAtRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    User getUserByUsername(String username);


}
