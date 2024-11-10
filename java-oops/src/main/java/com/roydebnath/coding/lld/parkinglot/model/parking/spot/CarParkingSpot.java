package com.roydebnath.coding.lld.parkinglot.model.parking.spot;

import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpotType;

public class CarParkingSpot extends ParkingSpot {
    public CarParkingSpot(String id) {
        super(id, ParkingSpotType.CAR);
    }
}
