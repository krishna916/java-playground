package com.krishnamurti.playground.methodHiding;

class SuperClass {
    public static void display() {
        System.out.println("Display method in SuperClass");
    }
}

class SubClass extends SuperClass {
    public static void display() {
        System.out.println("Display method in SubClass");
    }
}

public class MethodHiding {
    public static void main(String[] args) {
        // will execute subclas display method
        SubClass.display();
        // will execute superclass display method
        SuperClass.display();

        // will execute subclas display method
        SubClass obj = new SubClass();
        obj.display();
        // will execute superclass display method
        SuperClass obj2 = new SubClass();
        obj2.display();

    }
}
