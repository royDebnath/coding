package com.java.ooad.parkinglot.model.parking.spot;


import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpot;
import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpotType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbledCarParkingSpot extends ParkingSpot {
    public AbledCarParkingSpot(String id) {
        super(id, ParkingSpotType.ABLED);
    }
}
