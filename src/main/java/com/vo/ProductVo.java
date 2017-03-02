/*
 * Copyright the original author or authors.
 */

package com.vo;

/**
 * Created by jay on 2016/12/20.
 */
public class ProductVo {

    private String id;

    private String name;

    private String rate;

    public ProductVo(String id, String name, String rate) {
        this.id = id;
        this.name = name;
        this.rate = rate;
    }

    public ProductVo(String name, String rate) {
        this.name = name;
        this.rate = rate;
    }

    public ProductVo() {
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
