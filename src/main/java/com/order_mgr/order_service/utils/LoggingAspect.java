package com.order_mgr.order_service.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.order_mgr.order_service.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint){
        logger.info("Entering method: {} ", joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.order_mgr.order_service.*.*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint){
        logger.info("Exiting Method: {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.order_mgr.order_service.*.*(..))", throwing = "error")
    public void logAfterException(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: {} with error: {}",
                joinPoint.getSignature().getName(),
                error.getMessage(),
                error);  // This also logs the full stack trace
    }
}
