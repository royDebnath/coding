package com.roydebnath.coding.lld.parkinglot.model.parking;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ParkingLot {
    private String parkingLotId;

    private List<ParkingFloor> parkingFloors;

    public static ParkingLot INSTANCE = new ParkingLot();

    private ParkingLot() {
        this.parkingLotId = UUID.randomUUID().toString();
        parkingFloors = new ArrayList<>();
    }
}
