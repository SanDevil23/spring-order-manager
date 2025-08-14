package com.order_mgr.order_service.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.order_mgr.order_service.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint){
        System.out.println("Entering method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.order_mgr.order_service.*.*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint){
        System.out.println("Exiting method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.order_mgr.order_service.*.*(..))", throwing = "error")
    public void logAfterException(JoinPoint joinPoint, Throwable error) {
        System.out.println("Exception in method: " + joinPoint.getSignature().getName() + " with error: " + error.getMessage());
    }
}
