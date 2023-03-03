package com.java.patterns.creational.abstractfactory.impl.types;

import com.java.patterns.creational.abstractfactory.interfaces.Shape;

public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}