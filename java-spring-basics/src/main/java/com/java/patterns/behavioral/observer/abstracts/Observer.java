package com.java.patterns.behavioral.observer.abstracts;


import com.java.patterns.behavioral.observer.subject.Subject;

public abstract class Observer {

    protected Subject subject;

    public abstract void update();

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

}