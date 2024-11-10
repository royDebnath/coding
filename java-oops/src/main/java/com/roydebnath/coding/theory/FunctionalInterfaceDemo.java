package com.roydebnath.coding.theory;

import java.util.List;

/**
 * A functional interface is an interface that contains only one abstract method.
 * They can have only one functionality to exhibit. From Java 8 onwards,
 * lambda expressions can be used to represent the instance of a functional interface.
 * A functional interface can have any number of default methods. Runnable, ActionListener,
 * Comparable are some examples of functional interfaces.
 */
@FunctionalInterface
interface Condition {
    boolean test(String s);
}

public class FunctionalInterfaceDemo {
    public static void main(String agrs[]) {

        List<String> list = List.of("axxx", "byyy", "czzz");

        //Anonymous class implementation
        Condition condition = new Condition() {
            public boolean test(String s) {

                if (s.contains("x")) {
                    return true;
                }
                return false;
            }
        };

        invokeCondition(list, s -> s.contains("y")); //invoke through lambda
        invokeCondition(list, condition); // invoke through implemented class

        list.stream().filter(s -> s.contains("x")).map(String::toUpperCase).forEach(System.out::println);
        list.stream().filter(s -> s.contains("x")).map(String::toUpperCase).forEach(System.out::println);
    }

    private static void invokeCondition(List<String> list, Condition condition) {
        for (String l : list) {
            if (condition.test(l)) {
                System.out.print(l);
            }
        }
    }
}