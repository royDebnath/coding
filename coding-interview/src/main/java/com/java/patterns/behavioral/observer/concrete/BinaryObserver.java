package com.java.patterns.behavioral.observer.concrete;


import com.java.patterns.behavioral.observer.abstracts.Observer;
import com.java.patterns.behavioral.observer.subject.Subject;

public class BinaryObserver extends Observer {

   public BinaryObserver(Subject subject){
      super(subject);
   }

   @Override
   public void update() {
      System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) ); 
   }
}