package com.java.patterns.structural.bridge;

/**
 * decouple an abstraction from its implementation so that the two can vary independently.
 * This means to create a bridge interface that uses OOP principles
 * to separate out responsibilities into different abstract classes.
 *
 * Here, we're using the Bridge pattern and passing the desired color object.
 * As we can note in the output, the shape gets drawn with the desired color:
 *
 */
public class BridgeDemo {
    public static void main(String[] args) {
        Shape square = new Square(new Blue());
        System.out.println(square.draw());
    }
}
