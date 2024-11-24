package com.example.designpattern.dynamicProxy.cglib;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentAspect {

    //Student class 配置前都會前置方法
    @Before(value = "execution(* com.example.designpattern.dynamicProxy.cglib.Student.*(..))")
    public void before() {
        System.out.println("Before Student cglib");
    }
}
