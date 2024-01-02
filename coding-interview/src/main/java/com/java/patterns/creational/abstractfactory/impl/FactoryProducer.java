package com.java.patterns.creational.abstractfactory.impl;


import com.java.patterns.creational.abstractfactory.impl.factories.ColorFactory;
import com.java.patterns.creational.abstractfactory.impl.factories.ShapeFactory;
import com.java.patterns.creational.abstractfactory.interfaces.AbstractFactory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {

		if (choice.equalsIgnoreCase("SHAPE")) {
			return new ShapeFactory();

		} else if (choice.equalsIgnoreCase("COLOR")) {
			return new ColorFactory();
		}

		return null;
	}
}