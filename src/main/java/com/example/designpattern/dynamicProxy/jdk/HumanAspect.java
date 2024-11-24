package com.example.designpattern.dynamicProxy.jdk;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HumanAspect {


    //Student class 配置前都會前置方法
    @Before(value = "execution(* com.example.designpattern.dynamicProxy.jdk.Student.*(..))")
    public void before() {
        System.out.println("Before Student");
    }
}
