package com.example.designpattern.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;;

import java.sql.Time;

@Aspect
@Component
public class RunTimeCalAop {

    @Around(value = "@annotation(com.example.designpattern.Aspect.RunTimeCal)")
    public Object CalculateRunTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object returnObject = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("test");
        System.out.printf("Running time in %d milliseconds.%n", end - start);
        return returnObject;
    }
}
