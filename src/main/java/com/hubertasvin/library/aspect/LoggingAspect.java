package com.hubertasvin.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.hubertasvin.library.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Prieš metodą: " + joinPoint.getSignature().getName());
    }
}
