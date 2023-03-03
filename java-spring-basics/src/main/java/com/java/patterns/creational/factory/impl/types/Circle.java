package com.java.patterns.creational.factory.impl.types;


import com.java.patterns.creational.factory.interfaces.Shape;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
}