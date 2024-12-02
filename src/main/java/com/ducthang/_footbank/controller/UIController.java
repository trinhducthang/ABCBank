package com.ducthang._footbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }


    @GetMapping("/loanOffers")
    public String loanOffers() {
        return "loanOffer";
    }

    @GetMapping("/createLoan")
    public String createLoan() {
        return "loanof";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/dangxuat")
    public String dangxuat(){
        return "logout";
    }

    @GetMapping("/transfer")
    public String transfer() {
        return "transfer";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/payment-success")
    public String paymentSuccess() {
        return "payment-success";
    }

    @GetMapping("/payment-failure")
    public String paymentFailure() {
        return "payment-failure";
    }

    @GetMapping("/dashboard/loan-offers")
    public String dashboardLoanOffers() {
        return "loanofferadmin";
    }


    @GetMapping("/dashboard/transaction-details")
    public String dashboardTransactionDetails() {
        return "transactiondetalis";
    }

    @GetMapping("/dashboard/transaction-details/monthly-summary")
    public String abcdxyz() {
        return "transactions-details-chart";
    }


    @GetMapping("dashboard/account-bank")
    public String accountBank() {
        return "accountbank";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(){
        return "forgotPassword";
    }
}
