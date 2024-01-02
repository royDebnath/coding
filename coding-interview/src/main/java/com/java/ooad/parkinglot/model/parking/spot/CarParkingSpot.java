package com.java.ooad.parkinglot.model.parking.spot;


import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpot;
import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpotType;

public class CarParkingSpot extends ParkingSpot {
    public CarParkingSpot(String id) {
        super(id, ParkingSpotType.CAR);
    }
}
