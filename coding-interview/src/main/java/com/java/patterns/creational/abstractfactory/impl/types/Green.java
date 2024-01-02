package com.java.patterns.creational.abstractfactory.impl.types;

import com.java.patterns.creational.abstractfactory.interfaces.Color;

public class Green implements Color {

	@Override
	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}
}