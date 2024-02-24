package com.example.designpattern.builder;

public interface IProductBuilder {
    IProductBuilder Reset();
    IProductBuilder SetPrice(int price);
    IProductBuilder SetName(String name);
    Product GetProduct();

}
