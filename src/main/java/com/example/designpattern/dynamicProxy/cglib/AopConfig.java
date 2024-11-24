package com.example.designpattern.dynamicProxy.cglib;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.example.designpattern.dynamicProxy.cglib")
@EnableAspectJAutoProxy
public class AopConfig {

    // 测试方法
    @Test
    public void testProxy() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AopConfig.class);
        //因為Student 為實作介面，因此 spring 使用 cglib 產生代理類
        Student student =  context.getBean(Student.class);
        student.displayFinal();
        student.displayProtect();
        student.displayPublic();
        // 输出代理类的父类，以此判断是JDK还是CGLib
        // 因為是 CGLIB , 所以父類是 class com.example.designpattern.dynamicProxy.cglib.Student
        System.out.println(student.getClass().getSuperclass());
    }
}
