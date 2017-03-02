/*
 * Copyright the original author or authors.
 */

package com.configure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by jay on 2016/12/21.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.repository.jpa")
@EntityScan(basePackages = "com.entity.jpa")
public class JpaConfigure {
}
