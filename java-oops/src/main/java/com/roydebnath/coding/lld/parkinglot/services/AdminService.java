package com.roydebnath.coding.lld.parkinglot.services;


import com.roydebnath.coding.lld.parkinglot.exceptions.InvlaidParkingFloorException;
import com.roydebnath.coding.lld.parkinglot.model.parking.ParkingFloor;
import com.roydebnath.coding.lld.parkinglot.model.parking.ParkingLot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpot;

import java.util.Optional;

public class AdminService {

    ParkingLot parkingLot = ParkingLot.INSTANCE;

    public void addParkingFloor(ParkingFloor parkingFloor) {
        /**Checks if floor with same floor id exists*/
        Optional<ParkingFloor> floor =
                parkingLot.getParkingFloors().stream()
                        .filter(pF -> pF.getFloorId().equalsIgnoreCase(parkingFloor.getFloorId()))
                        .findFirst();
        if (floor.isPresent())
            return;
        parkingLot.getParkingFloors().add(parkingFloor);
    }

    public void addParkingSpot(String parkingFloorId, ParkingSpot parkingSpot)
            throws InvlaidParkingFloorException {
        /**Checks if floor with same floor id exists*/
        Optional<ParkingFloor> floor =
                parkingLot.getParkingFloors().stream()
                        .filter(pF -> pF.getFloorId().equalsIgnoreCase(parkingFloorId))
                        .findFirst();
        if (!floor.isPresent())
            throw new InvlaidParkingFloorException("Invalid floor");

        /**Checks if spot id is already there*/
        Optional<ParkingSpot> spot =
                floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType())
                        .stream()
                        .filter(pS -> pS.getParkingSpotId().equalsIgnoreCase(parkingSpot.getParkingSpotId()))
                        .findFirst();
        if (spot.isPresent())
            return;

        floor.get().getParkingSpots().get(parkingSpot.getParkingSpotType())
                .addLast(parkingSpot);
    }
}
