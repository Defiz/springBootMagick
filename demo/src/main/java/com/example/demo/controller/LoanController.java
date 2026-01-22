package com.example.demo.controller;

import com.example.demo.service.LoanCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoanController {

    private final LoanCalculationService loanCalculationService;

    @GetMapping("/loan")
    public double approveLoan(@RequestParam Integer userId) {
        return loanCalculationService.calculateMaxCreditByUser(userId);
    }
}
