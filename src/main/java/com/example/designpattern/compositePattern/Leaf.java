package com.example.designpattern.compositePattern;
public class Leaf implements Component{
    String LeafName;

    public Leaf(String name){
        this.LeafName = name;
    }

    @Override
    public void inflate() {
        System.out.println("Leaf inflate");
        System.out.println("Leaf name: " + LeafName);
    }
}
