package com.example.designpattern.flyweight;

import com.example.designpattern.Aspect.RunTimeCal;
import org.springframework.stereotype.Service;

@Service
public class FlyweightTest {
    public static void main(String[] args) {
        Run();
    }

    @RunTimeCal
    public static void Run() {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        Flyweight fly1 = flyweightFactory.getFlyweight("java");
        Flyweight fly2 = flyweightFactory.getFlyweight("hibernate");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
