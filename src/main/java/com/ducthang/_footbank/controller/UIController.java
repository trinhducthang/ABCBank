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
}
