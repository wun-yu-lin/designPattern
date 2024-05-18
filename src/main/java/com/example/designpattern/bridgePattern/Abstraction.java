package com.example.designpattern.bridgePattern;


/**
 * 抽象化角色
 */
abstract class Abstraction {
    protected Implementor implementor;
    protected Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}
