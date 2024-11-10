package com.roydebnath.coding.theory;

/*
 * Java 8 introduces a new concept of default method implementation in interfaces.
 * This capability is added for backward
 * compatibility so that old interfaces can be used to leverage the lambda expression capability of Java 8.
 *  For example,
 * ‘List’ or ‘Collection’ interfaces do not have ‘forEach’ method declaration.
 * Thus, adding such method will simply
 * break the collection framework implementations.
 * Java 8 introduces default method so that List/Collection interface
 * can have a default implementation of forEach method, and the class implementing these interfaces
 *  need not implement the same.
 *
 * Implementing class may/may not override the default method. Like class Truck does not override the default method so
 * it outputs the statement of interface Vehicle, whereas class Tempo overrides it and ouputs its own statement.
 *
 * But class car must override the default method because it implements both Vehicle and FourWheeler interface who
 * both have default method of same name. so to remove ambiguity it must override.
 *
 * static method cannot be called by implementing subclass
 * subclass cannot override static method
 *
 * static method has the advantage of utility method, default method is the default behavior for all implementing classes
 * unless overridden
 *
 *
 */
public class DefaultMethodImplementation {

    public static void main(String args[]) {
        Vehicle car = new Car();
        Vehicle truck = new Truck();
        Vehicle tempo = new Tempo();
        car.print();
        truck.print();
        tempo.print();
        Vehicle.blowHorn();
        //car.blowHorn(); does not work static method of interface can be invoked by interface only
    }
}

interface Vehicle {

    default void print() {
        System.out.println("I am a vehicle!");
    }
    default void printNew(){};

    static void blowHorn() {
        System.out.println("Blowing horn!!!");
    }
    static void anotherStatic(){};
}

interface FourWheeler {

    default void print() {
        System.out.println("I am a four wheeler!");
    }
}

class Truck implements Vehicle {

}

class Tempo implements Vehicle {

    @Override
    public void print() {
        System.out.println("I am a tempo!");
    }

}

class Car implements Vehicle, FourWheeler {

    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("I am a car!");
    }
}