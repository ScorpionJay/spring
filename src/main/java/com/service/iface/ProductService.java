/*
 * Copyright the original author or authors.
 */

package com.service.iface;


import com.vo.ProductVo;

import java.util.List;

/**
 * Created by jay on 2016/12/20.
 */
public interface ProductService {

    void add(ProductVo productVo);

    List<ProductVo> findAll();

    List<ProductVo> testJpa();

    List<ProductVo> testMybaties();

    List<ProductVo> testMybaties2();

    void save(ProductVo vo);

}
