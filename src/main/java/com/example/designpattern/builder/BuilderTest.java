package com.example.designpattern.builder;

public class BuilderTest {
    public static void main(String[] args) {
        Product product1 = new PhoneProductBuilder()
                .SetName("IPhone")
                .SetPrice(52000)
                .GetProduct();


        Product product2 = new PhoneProductBuilder()
                .SetName("Pixel")
                .SetPrice(17000)
                .GetProduct();

    }
}
