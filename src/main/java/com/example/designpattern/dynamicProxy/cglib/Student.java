package com.example.designpattern.dynamicProxy.cglib;

import org.springframework.stereotype.Component;

@Component
public class Student {

    public void displayPublic(){
        System.out.println("displayPublic");
    }

    private void displayPrivate(){
        System.out.println("displayPrivate");
    }

    public final void displayFinal(){
        System.out.println("displayFinal");
    }

    public final void displayProtect(){
        System.out.println("displayProtect");
    }

}
