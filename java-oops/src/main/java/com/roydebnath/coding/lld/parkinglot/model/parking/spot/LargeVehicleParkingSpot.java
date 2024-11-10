package com.roydebnath.coding.lld.parkinglot.model.parking.spot;

import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpotType;

public class LargeVehicleParkingSpot extends ParkingSpot {
    public LargeVehicleParkingSpot(String id) {
        super(id, ParkingSpotType.LARGE);
    }
}
