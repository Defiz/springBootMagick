package com.example.demo.controller;

import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String findAll(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars";
    }

    @GetMapping(value = "/cars", params = "count")
    public String showCars(@RequestParam int count, @RequestParam(defaultValue = "id") String sortBy, Model model) {
        try {
            model.addAttribute("cars", carService.getCar(count, sortBy));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return "cars";
    }
}
