package com.java.ooad.parkinglot.model.parking;


import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpot;
import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpotType;
import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

import static com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpotType.*;


@Getter
@Setter
public class ParkingFloor {
    @Getter
    @Setter
    private String floorId;
    @Getter
    private Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots = new HashMap<>();
    private Map<String, ParkingSpot> usedParkingSpots = new HashMap<>();

    public ParkingFloor(String id) {
        this.floorId = id;
        parkingSpots.put(ABLED, new ConcurrentLinkedDeque());
        parkingSpots.put(CAR, new ConcurrentLinkedDeque());
        parkingSpots.put(LARGE, new ConcurrentLinkedDeque());
        parkingSpots.put(MOTORBIKE, new ConcurrentLinkedDeque());
        parkingSpots.put(ELECTRIC, new ConcurrentLinkedDeque());
    }
}
