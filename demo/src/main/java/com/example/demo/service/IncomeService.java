package com.example.demo.service;

import com.example.demo.dto.LoanDto;
import com.example.demo.exeption.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@ConfigurationProperties(prefix = "income-api-url")
@Setter
public class IncomeService {

    private String url;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    @Autowired
    public IncomeService(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    public Integer getIncomeById(Integer userId) {
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new UserNotFoundException("User not found");
        }
        return getAllIncomes().stream()
                .filter(i -> i.getId().equals(userId))
                .map(LoanDto::getIncome)
                .findFirst()
                .orElse(0);
    }

    public List<LoanDto> getAllIncomes() {
        LoanDto[] response = restTemplate.getForObject(url, LoanDto[].class);
        return Arrays.asList(response);
    }
}
