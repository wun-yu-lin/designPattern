package com.example.designpattern.dynamicProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamicProxyDemo2 {
    @Autowired
    DynamicProxyDemo1 dynamicProxyDemo1;

    public void invoke() {
        //invoke spring proxy method
        dynamicProxyDemo1.method2();;
    }

}
