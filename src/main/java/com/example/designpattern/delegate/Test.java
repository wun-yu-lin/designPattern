package com.example.designpattern.delegate;

public class Test {


    public SubClass getSubClass() {

        //Student student =  dao.getById(); => entity;

        //Object => Serializable (get ? )
        return new SubClass(new StudentClass());
    }
}
