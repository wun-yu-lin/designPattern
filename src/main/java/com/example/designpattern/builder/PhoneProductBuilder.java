package com.example.designpattern.builder;

public class PhoneProductBuilder implements IProductBuilder{
    private Product product;

    public PhoneProductBuilder(){
        product = new Product();
    }

    @Override
    public IProductBuilder Reset() {
        product = new Product();
        return this;
    }

    @Override
    public IProductBuilder SetPrice(int price) {
        product.setPrice(price);
        return this;
    }

    @Override
    public IProductBuilder SetName(String name) {
        product.setName(name);
        return this;
    }

    @Override
    public Product GetProduct() {
        return product;
    }
}
