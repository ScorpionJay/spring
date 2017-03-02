/*
 * Copyright the original author or authors.
 */

package com.entity.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jay on 2016/12/21.
 */
@Entity
@Table(name="product")
public class Product implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String name;

    private Integer rate;

    public Product() {
    }

    public Product(Integer id, String name, Integer rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
