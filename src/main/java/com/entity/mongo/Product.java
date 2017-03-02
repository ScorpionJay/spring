/*
 * Copyright the original author or authors.
 */

package com.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jay on 2016/12/20.
 */
@Document(collection="product")
public class Product {

    @Id
    private String id;

    private String name;

    private String rate;

    public Product(String name, String rate) {
        this.name = name;
        this.rate = rate;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
