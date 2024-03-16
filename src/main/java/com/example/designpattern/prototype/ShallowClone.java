package com.example.designpattern.prototype;

public class ShallowClone implements Cloneable {
    public String name;
    public String description;
    public int age;


    //constructor
    public ShallowClone() {
        this.name = "wunyu";
        this.description = "Wun yu test clone";
        this.age = 18;
    }


    @Override
    public ShallowClone clone() {
        System.out.println("ShallowClone clone");
        try {
            return (ShallowClone) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void print() {
        System.out.println(
                "This hashCode: " + hashCode() +
                        "\nName: " + name + ", " + name.hashCode() +
                        "\nDescribe:" + description + ", " + description.hashCode());
    }

    public static void main(String[] args) {
        System.out.println("\nOriginal----------------Create original Object:");
        ShallowClone shallowClone = new ShallowClone();
        shallowClone.print();

        System.out.println("\nClone----------------Start clone:");
        ShallowClone shallowClone1 = shallowClone.clone();
        shallowClone1.print();

        System.out.println("\nOriginal----------------Change original Object:");
        shallowClone.name = "cloneName";
        shallowClone.description = "Clone Name";
        shallowClone.print();
        System.out.println("\nOriginal----------------After clone change, original Object:");
        shallowClone.print();
        System.out.println("\nOriginal----------------clone Object:");
        shallowClone1.print();
    }

}
