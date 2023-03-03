package com.java.patterns.behavioral.observer.concrete;


import com.java.patterns.behavioral.observer.abstracts.Observer;
import com.java.patterns.behavioral.observer.subject.Subject;

public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}