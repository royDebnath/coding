package com.java.patterns.creational.abstractfactory.interfaces;

public abstract class AbstractFactory {
	public abstract Color getColor(String color);

	public abstract Shape getShape(String shape);
}