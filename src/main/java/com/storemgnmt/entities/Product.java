package com.storemgnmt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    private Integer stock;
    private BigDecimal unitaryPrice;

    public Product(){

    }
    public Product(Long id, String name, Integer stock, BigDecimal unitaryPrice) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.unitaryPrice = unitaryPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(BigDecimal unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public Long getId() {
        return id;
    }
}
