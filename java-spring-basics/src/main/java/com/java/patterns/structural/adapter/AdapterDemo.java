package com.java.patterns.structural.adapter;

/**
 *the adapter pattern is defined as allowing incompatible classes to work together
 * by converting the interface of one class into another expected by the clients
 *
 * There are the following specifications for the adapter pattern:
 *
 * Target Interface: This is the desired interface class which will be used by the clients.
 *
 * Adapter class: This class is a wrapper class which implements the desired target interface
 * and modifies the specific request available from the Adaptee class.
 *
 * Adaptee class: This is the class which is used by the Adapter class to reuse
 * the existing functionality and modify them for desired use.
 *
 * Client: This class will interact with the Adapter class.
 *
 * Consider a scenario in which there is an app that's developed in the US which returns the top speed
 * of luxury cars in miles per hour (MPH). Now we need to use the same app for our client in the UK
 * that wants the same results but in kilometers per hour (km/h).
 *
 * To deal with this problem, we'll create an adapter which will convert the values
 * and give us the desired results:
 */
public class AdapterDemo {
    public static void main(String[] args) {
        Movable bugattiVeyron = new BugattiVeyron();
        MovableAdapter bugattiVeyronAdapter = new MovableAdapterImpl(bugattiVeyron);

        System.out.println(bugattiVeyronAdapter.getSpeed());
    }
}
