package com.java.patterns.creational.abstractfactory.impl.factories;


import com.java.patterns.creational.abstractfactory.impl.types.Green;
import com.java.patterns.creational.abstractfactory.impl.types.Red;
import com.java.patterns.creational.abstractfactory.interfaces.AbstractFactory;
import com.java.patterns.creational.abstractfactory.interfaces.Color;
import com.java.patterns.creational.abstractfactory.interfaces.Shape;

public class ColorFactory extends AbstractFactory {

	@Override
	public Shape getShape(String shapeType) {
		return null;
	}

	@Override
	public Color getColor(String color) {

		if (color == null) {
			return null;
		}

		if (color.equalsIgnoreCase("RED")) {
			return new Red();

		} else if (color.equalsIgnoreCase("GREEN")) {
			return new Green();

		}

		return null;
	}
}