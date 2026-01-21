package com.example.demo.controller;

import com.example.demo.service.LoanCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    private final LoanCalculationService loanCalculationService;

    @Autowired
    public LoanController(LoanCalculationService loanCalculationService) {
        this.loanCalculationService = loanCalculationService;
    }

    @GetMapping("/loan")
    public double approveLoan(@RequestParam Integer userId) {
        return loanCalculationService.calculateMaxCreditByUser(userId);
    }
}
