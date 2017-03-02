/*
 * Copyright the original author or authors.
 */

package com.repository.jpa;

import com.entity.jpa.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jay on 2016/12/21.
 */
public interface ProductRepository extends CrudRepository<Product,Integer> {
}
