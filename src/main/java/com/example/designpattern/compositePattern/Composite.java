package com.example.designpattern.compositePattern;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Composite implements Component{

    private List<Component> list = new LinkedList<>();

    public void add(Component component) {
        list.add(component);
    }

    public void remove(Component component) {
        list.remove(component);
    }

    @Override
    public void inflate() {
        System.out.println("Composite inflate");
        for (Component component : list) {
            component.inflate();
        }
    }
}
