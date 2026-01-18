package com.example.demo.service;

import com.example.demo.config.CarConfig;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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
        List<Car> cars = carRepository.findAll();
        if (sortBy != null) {
            if (!carConfig.getSortable().contains(sortBy)) {
                throw new IllegalArgumentException("sort not supported");
            }
            switch (sortBy) {
                case "id":
                    cars.sort(Comparator.comparing(Car::getId));
                    break;
                case "model":
                    cars.sort(Comparator.comparing(Car::getModel));
                    break;
            }
        }
        int limit = Math.min(count, carConfig.getMaxCar());
        return cars.stream()
                .limit(limit)
                .toList();
    }
}
