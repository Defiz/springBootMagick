package com.example.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "cars")
public class CarConfig {

    private int maxCar;
    private List<String> sortable;

    public int getMaxCar() {
        return maxCar;
    }

    public List<String> getSortable() {
        return sortable;
    }

    public void setMaxCar(int maxCar) {
        this.maxCar = maxCar;
    }

    public void setSortable(List<String> sortable) {
        this.sortable = sortable;
    }
}
