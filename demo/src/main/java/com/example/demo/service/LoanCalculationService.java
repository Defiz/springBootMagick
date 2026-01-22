package com.example.demo.service;

import com.example.demo.config.LoanConfig;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoanCalculationService {

    private final LoanConfig loanConfig;
    private final IncomeService incomeService;
    private final CarRepository carRepository;
    private final int MONTHS = 12;


    public Integer maxByCar(Car car) {
        if (car.getPrice() < loanConfig.getMinimalCarPrice()) {
            return 0;
        }
        return (int) (car.getPrice() * loanConfig.getCar().getMaxPercentage());
    }

    public Integer maxByIncome(Integer income) {
        if (income < loanConfig.getMinimalIncome()) {
            return 0;
        }
        return (int) (income * MONTHS * loanConfig.getIncome().getMaxPercentage());
    }

    public Integer calculateMaxCreditByUser(Integer userId) {
        Integer userIncome = incomeService.getIncomeById(userId);
        Car userCar = carRepository.findByUserId(userId);
        Integer maxByCar = maxByCar(userCar);
        Integer maxByIncome = maxByIncome(userIncome);
        return Math.max(maxByIncome, maxByCar);
    }
}

