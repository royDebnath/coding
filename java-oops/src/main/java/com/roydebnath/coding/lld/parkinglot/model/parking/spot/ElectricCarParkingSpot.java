package com.roydebnath.coding.lld.parkinglot.model.parking.spot;

import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpotType;

public class ElectricCarParkingSpot extends ParkingSpot {
    public ElectricCarParkingSpot(String id) {
        super(id, ParkingSpotType.ELECTRIC);
    }
}
