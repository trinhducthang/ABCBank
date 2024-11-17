package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.entity.*;
import com.ducthang._footbank.repository.AccountRepository;
import com.ducthang._footbank.repository.LoanOfferRepository;
import com.ducthang._footbank.repository.LoanRepository;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    private final LoanOfferRepository loanOfferRepository;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan createLoan(Long userId, Long loanOfferId) {
        // Kiểm tra xem người dùng có tồn tại không
        checkExits(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));


        // Kiểm tra xem người dùng đã có khoản vay chưa
        Loan existingLoan = loanRepository.findByUserId(userId);
        if (existingLoan != null) {
            throw new RuntimeException("User already has an existing loan");
        }

        // Lấy thông tin khoản vay từ LoanOffer
        LoanOffer loanOffer = loanOfferRepository.findById(loanOfferId)
                .orElseThrow(() -> new RuntimeException("Loan offer not found"));

        // Tạo mới khoản vay
        Loan loan = new Loan();
        loan.setLoanOffer(loanOffer);
        loan.setStatus("Completed");


        // Gán người dùng vào khoản vay (danh sách users)
        loan.setUsers(Collections.singletonList(user));  // Gán người dùng vào danh sách users của Loan

        // Lấy danh sách tài khoản ngân hàng của người dùng
        Set<AccountBank> accountBanks = accountRepository.findByUserId(userId);
        if (accountBanks == null || accountBanks.isEmpty()) {
            throw new RuntimeException("No bank accounts found for user");
        }

        // Cập nhật số dư của tài khoản ngân hàng (giả sử cập nhật tài khoản đầu tiên)
        AccountBank accountBank = accountBanks.iterator().next();  // Giả sử có ít nhất 1 tài khoản
        accountBank.setBalance(accountBank.getBalance().add(loanOffer.getLoanAmount()));

        // Lưu tài khoản ngân hàng và khoản vay
        accountRepository.save(accountBank); // Lưu tài khoản ngân hàng đã cập nhật
        Loan savedLoan = loanRepository.save(loan);  // Lưu khoản vay mới
        user.setLoan(savedLoan);
        userRepository.save(user);
        return savedLoan;
    }




    @Override
    public LoanDetail fastLoan(Long userId) {
        Set<AccountBank> accountBanks = accountRepository.findByUserId(userId);
        if (accountBanks != null) {
            throw new RuntimeException("Your account bank not exits");
        }

        return null;
    }

    @Override
    public boolean pay(Long userId, Long loanId) {
//        Set<AccountBank> accountBanks = accountRepository.findByUserId(userId);
//        LoanOffer = loanRepository.
//        for (AccountBank accountBank : accountBanks) {
//            if(accountBank.getBalance() < )
//        }
        return false;
    }

    private boolean checkExits(Long userId){
        User user = userRepository.findById(userId).get();
        if (user.getLoan() == null){
            return false;
        }
        else throw new RuntimeException("This account has existed loans");
    }






}
