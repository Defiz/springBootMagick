package com.example.demo.model;

import jakarta.persistence.*;
import org.apache.catalina.Engine;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @Column(name = "price")
    private Long price;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private User user;

    public Car(String model, int series, Long price) {
        this.price = price;
        this.model = model;
        this.series = series;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public Long getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
