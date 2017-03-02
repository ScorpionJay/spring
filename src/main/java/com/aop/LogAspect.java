/*
 * Copyright the original author or authors.
 */

package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by jay on 2016/12/26.
 */
@Component
@Aspect
@Order(value = 1)
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.controller.*.*(..))")
    public void beforeController(JoinPoint jp) {
        //String className = jp.getThis().toString();
        String methodName = jp.getSignature().getName(); // 获得方法名
        logger.info("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        //System.out.println("====位于：" + className);
        logger.info("====调用" + methodName + " 方法-开始！");
        Object[] args = jp.getArgs(); // 获得参数列表
        if (args.length <= 0) {
            logger.info("====" + methodName + "方法没有参数");
        } else {
            for (int i = 0; i < args.length; i++) {
                logger.info("====参数  " + (i + 1) + "：" + args[i]);
            }
        }
        logger.info("=====================================");
    }


//    @Around(value = "execution(* com.controller.*.*(..))")
//    public Object aroundController(ProceedingJoinPoint pjp)  throws Throwable{
//        logger.info("2》》》》》》》》》》》》》》》》》》》》》》》》》》》");
//        Object data = pjp.proceed();
//        logger.info(data.toString());
//        return data;
//    }

    @After(value = "execution(* com.controller.*.*(..))")
    public void afterController(JoinPoint jp) {
        logger.info("=====================================");
        logger.info("====" + jp.getSignature().getName() + " 方法-结束！");
        logger.info("《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《《");
    }

}