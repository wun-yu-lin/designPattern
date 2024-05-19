package com.example.designpattern.compositePattern;

public class CompositeTest {
    public static void main(String[] args) {
        Component c1 = new Leaf("Leaf1");
        Component c2 = new Leaf("Leaf2");
        Component branch = new Composite();
        Component c3 = new Leaf("Leaf3");
        Component c4 = new Leaf("Leaf4");

        Component root = new Composite();
        branch.add(c3);
        branch.add(c4);
        root.add(c1);
        root.add(c2);
        root.add(branch);
        root.inflate();
    }
}
