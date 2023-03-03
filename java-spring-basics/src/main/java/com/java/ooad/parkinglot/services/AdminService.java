package com.java.ooad.parkinglot.services;


import com.java.ooad.parkinglot.exceptions.InvlaidParkingFloorException;
import com.java.ooad.parkinglot.model.parking.ParkingFloor;
import com.java.ooad.parkinglot.model.parking.ParkingLot;
import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpot;

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
