/*
 * Copyright the original author or authors.
 */

package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.vo.ResultVo;

/**
 * Created by jay on 2016/12/26.
 */
@Component
@Aspect
@Order(2)
public class ResultDataAspect {

    private static Logger logger = LoggerFactory.getLogger(ResultDataAspect.class);


    @Around(value = "execution(* com.controller.*.*(..))")
    public Object aroundController(ProceedingJoinPoint pjp)  throws Throwable{
        Object data = pjp.proceed();

        logger.info(data.toString());
        if( data instanceof ResultVo ){
            return data;
        }else{
            ResultVo resultVo = new ResultVo();
            resultVo.setData(data);
            return resultVo;
        }
    }

}