package com.example.demo.controller;

import com.example.demo.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public String findAll(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars";
    }

    @GetMapping(value = "/cars", params = "count")
    public String showCars(@RequestParam int count, @RequestParam(defaultValue = "id") String sortBy, Model model) {
        model.addAttribute("cars", carService.getCar(count, sortBy));
        return "cars";
    }
}
