package com.roydebnath.coding.lld.parkinglot.model.parking.spot;

import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpotType;

public class MotorBikeParkingSpot extends ParkingSpot {
    public MotorBikeParkingSpot(String id) {
        super(id, ParkingSpotType.MOTORBIKE);
    }
}
