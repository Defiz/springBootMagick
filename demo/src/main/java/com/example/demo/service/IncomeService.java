package com.example.demo.service;

import com.example.demo.config.IncomeConfig;
import com.example.demo.dto.LoanDto;
import com.example.demo.exeption.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class IncomeService {

    private final IncomeConfig incomeConfig;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;


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
        LoanDto[] response = restTemplate.getForObject(incomeConfig.getUrl(), LoanDto[].class);
        return Arrays.asList(response);
    }
}
