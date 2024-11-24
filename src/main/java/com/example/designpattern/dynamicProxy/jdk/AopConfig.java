package com.example.designpattern.dynamicProxy.jdk;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.example.designpattern.dynamicProxy.jdk")
@EnableAspectJAutoProxy
public class AopConfig {

    // 测试方法
    @Test
    public void testProxy() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AopConfig.class);
        // 只能通过Human.class 取得，而無法通過 Student.class，因为在 Spring container 中，
        // 因为使用JDK動態代理，Ioc 容器中，存储的是一个型別為 Human的代理对象
        Human human =  context.getBean(Human.class);
        human.display();
        // 输出代理类的父类，以此判断是JDK还是CGLib
        // 如果是 java.lang.reflect.Proxy 代表是 jdk 動態代理, 因為繼承了 Proxy class, 所以 superClass 是 Proxy
        System.out.println(human.getClass().getSuperclass());
    }
}
