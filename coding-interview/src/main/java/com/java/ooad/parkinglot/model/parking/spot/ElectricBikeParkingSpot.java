package com.java.ooad.parkinglot.model.parking.spot;


import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpot;
import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpotType;

public class ElectricBikeParkingSpot extends ParkingSpot {
    public ElectricBikeParkingSpot(String id) {
        super(id, ParkingSpotType.EBIKE);
    }
}
