/*
 * Copyright the original author or authors.
 */

package com.service.impl;

import com.entity.jpa.Product;
import com.repository.jpa.ProductRepository;
import com.repository.mybatis.ProductRepository2;
import com.service.iface.ProductService;
import com.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by jay on 2016/12/20.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRepository2 productRepository2;


    @Override
    public void add(ProductVo productVo) {
        com.entity.mongo.Product product = new com.entity.mongo.Product();
        product.setName(productVo.getName());
        product.setRate(productVo.getRate());
        mongoTemplate.save(product);
    }

    @Override
    public List<ProductVo> findAll() {
        List<com.entity.mongo.Product> products =  mongoTemplate.findAll(com.entity.mongo.Product.class);
        List<ProductVo> list = new ArrayList<>();
        if(products!=null && !products.isEmpty()) {
            for (com.entity.mongo.Product product : products)
                list.add(new ProductVo(product.getId(),product.getName(),product.getRate()));
        }
        return list;
    }

    @Override
    public List<ProductVo> testJpa() {
        Iterable<Product> it =  productRepository.findAll();
        List<ProductVo> list = new ArrayList<>();
        for(Product product : it){
            list.add(new ProductVo(product.getId().toString(),product.getName(),product.getRate().toString()));
        }
        return list;
    }

    @Override
    public List<ProductVo> testMybaties() {
        List<Product> products = productRepository2.fingByName("基金B");
        List<ProductVo> list = new ArrayList<>();
        if(products!=null && !products.isEmpty()) {
            for (Product product : products)
                list.add(new ProductVo(product.getId().toString(),product.getName(),product.getRate().toString()));
        }
        return list;
    }

    @Override
    public List<ProductVo> testMybaties2() {
       // Product p = productRepository2.fingByName2();
        List<Product> products = productRepository2.fingByName2();
        List<ProductVo> list = new ArrayList<>();
        if(products!=null && !products.isEmpty()) {
            for (Product product : products)
                list.add(new ProductVo(product.getId().toString(),product.getName(),product.getRate().toString()));
        }
        return list;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(ProductVo vo) {
        Product product = new Product();
        product.setName(vo.getName());
        product.setRate(Integer.parseInt(vo.getRate()));
        productRepository2.save(product);
        //Integer.parseInt("asdf");
    }
}
