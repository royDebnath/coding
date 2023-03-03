package com.java.patterns.structural.decorator.concrete;


import com.java.patterns.structural.decorator.abstracts.Car;

public class BasicCar implements Car {

	@Override
	public void assemble() {
		System.out.print("Basic Car.");
	}

}