package com.example.demo.service;

import com.example.demo.config.CarConfig;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;
    private final CarConfig carConfig;

    @Autowired
    public CarService(CarRepository carRepository, CarConfig carConfig) {
        this.carRepository = carRepository;
        this.carConfig = carConfig;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> getCar(int count, String sortBy) {
        int limit = Math.min(count, carConfig.getMaxCar());
        if (sortBy != null) {
            if (!carConfig.getSortable().contains(sortBy)) {
                throw new IllegalArgumentException("sort not supported");
            }
        }
        PageRequest pageRequest = PageRequest.of(0, limit, Sort.by(sortBy));
        return carRepository.findAll(pageRequest).getContent();
    }
}
