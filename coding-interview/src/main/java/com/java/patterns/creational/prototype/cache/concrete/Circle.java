package com.java.patterns.creational.prototype.cache.concrete;


import com.java.patterns.creational.prototype.cache.abstracts.Shape;

public class Circle extends Shape {

   public Circle(){
     type = "Circle";
   }

   @Override
   public void draw() {
      System.out.println("Inside Circle::draw() method.");
   }
}