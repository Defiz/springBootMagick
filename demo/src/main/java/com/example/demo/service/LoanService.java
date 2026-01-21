package com.example.demo.service;

import com.example.demo.dto.LoanDto;
import com.example.demo.exeption.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoanService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    public List<LoanDto> getAllIncomes() {
        String url = "https://66055cd12ca9478ea1801f2e.mockapi.io/api/users/income";
        LoanDto[] response = restTemplate.getForObject(url, LoanDto[].class);
        return Arrays.asList(response);
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
}
