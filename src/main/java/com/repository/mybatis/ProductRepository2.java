/*
 * Copyright the original author or authors.
 */

package com.repository.mybatis;

import com.entity.jpa.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by jay on 2016/12/21.
 */
public interface ProductRepository2 {

    @Select("SELECT * FROM product WHERE name = #{name}")
    List<Product> fingByName(@Param("name") String name);

    List<Product> fingByName2();

    void save(@Param("product") Product product);

}
