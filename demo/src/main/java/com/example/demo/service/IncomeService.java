package com.example.demo.service;

import com.example.demo.dto.LoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class IncomeService {

    @Value("${income-api-url.url}")
    private String url;

    private final RestTemplate restTemplate;

    public Integer getIncomeById(Integer userId) {
        LoanDto[] response = restTemplate.getForObject(url, LoanDto[].class);
        return Arrays.stream(response)
                .filter(loan -> loan.getId().equals(userId))
                .map(LoanDto::getIncome)
                .findFirst()
                .orElse(0);
    }
}
