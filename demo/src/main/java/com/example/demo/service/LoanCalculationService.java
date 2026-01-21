package com.example.demo.service;

import com.example.demo.config.LoanConfig;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanCalculationService {

    private final LoanConfig loanConfig;
    private final LoanService loanService;
    private final CarRepository carRepository;

    @Autowired
    public LoanCalculationService(LoanConfig loanConfig1, LoanService loanService, CarRepository carRepository) {
        this.loanConfig = loanConfig1;
        this.loanService = loanService;
        this.carRepository = carRepository;
    }

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
        return (int) (income * loanConfig.getIncome().getYearlyMultiplier() * loanConfig.getIncome().getMaxPercentage());
    }

    public Integer calculateMaxCreditByUser(Integer userId) {
        Integer userIncome = loanService.getIncomeById(userId);
        Car userCar = carRepository.findByUserId(userId);
        Integer maxByCar = maxByCar(userCar);
        Integer maxByIncome = maxByIncome(userIncome);
        return Math.max(maxByIncome, maxByCar);
    }
}
