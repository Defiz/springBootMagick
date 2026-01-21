package com.example.demo.config;

import com.example.demo.model.Car;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "loan")
public class LoanConfig {
    private Integer minimalIncome;
    private Integer minimalCarPrice;
    private IncomeRules income;
    private CarRules car;

    @Getter
    @Setter
    public static class IncomeRules {
        private Double maxPercentage;
    }

    @Getter
    @Setter
    public static class CarRules {
        private Double maxPercentage;
    }
}
