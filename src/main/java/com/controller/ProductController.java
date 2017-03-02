/*
 * Copyright the original author or authors.
 */

package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.iface.ProductService;

/**
 * Created by jay on 2016/12/20.
 */
@Controller
@RequestMapping("product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    @ResponseBody
    public Object products() {
        return productService.findAll();
    }

    @RequestMapping("/productsMybatis")
    @ResponseBody
    public Object productsMybatis() {
        return productService.testMybaties();
    }

    @RequestMapping("/productsJpa")
    @ResponseBody
    public Object productsJpa() {
        return productService.testJpa();
    }

    @RequestMapping("/testAop")
    @ResponseBody
    public Object testAop() {
        return productService.testJpa();
    }

}
