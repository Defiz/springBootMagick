package com.example.demo.config;

import com.example.demo.model.Car;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "loan")
public class LoanConfig {

    private Integer minimalIncome;
    private Integer minimalCarPrice;
    private IncomeRules income;
    private CarRules car;

    public Integer getMinimalIncome() {
        return minimalIncome;
    }

    public Integer getMinimalCarPrice() {
        return minimalCarPrice;
    }

    public IncomeRules getIncome() {
        return income;
    }

    public CarRules getCar() {
        return car;
    }

    public void setMinimalIncome(Integer minimalIncome) {
        this.minimalIncome = minimalIncome;
    }

    public void setCar(CarRules car) {
        this.car = car;
    }

    public void setIncome(IncomeRules income) {
        this.income = income;
    }

    public void setMinimalCarPrice(Integer minimalCarPrice) {
        this.minimalCarPrice = minimalCarPrice;
    }

    public static class IncomeRules {

        private Integer yearlyMultiplier;
        private Double maxPercentage;

        public Integer getYearlyMultiplier() {
            return yearlyMultiplier;
        }

        public Double getMaxPercentage() {
            return maxPercentage;
        }

        public void setYearlyMultiplier(Integer yearlyMultiplier) {
            this.yearlyMultiplier = yearlyMultiplier;
        }

        public void setMaxPercentage(Double maxPercentage) {
            this.maxPercentage = maxPercentage;
        }
    }

    public static class CarRules {

        private Double maxPercentage;

        public Double getMaxPercentage() {
            return maxPercentage;
        }

        public void setMaxPercentage(Double maxPercentage) {
            this.maxPercentage = maxPercentage;
        }

    }
}
