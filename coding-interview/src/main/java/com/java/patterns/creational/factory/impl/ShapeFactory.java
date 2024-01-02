package com.java.patterns.creational.factory.impl;


import com.java.patterns.creational.factory.impl.types.Circle;
import com.java.patterns.creational.factory.impl.types.Rectangle;
import com.java.patterns.creational.factory.impl.types.Square;
import com.java.patterns.creational.factory.interfaces.Shape;

public class ShapeFactory {

	// use getShape method to get object of type shape
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();

		} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();

		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}

		return null;
	}
}