package com.example.designpattern.compositePattern;

public interface Component {
    void add (Component component);
    void remove (Component component);

    void inflate();
}
