package com.java.ooad.parkinglot.services;


import com.java.ooad.parkinglot.model.parking.ParkingFloor;
import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpot;
import com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpotType;
import com.java.ooad.parkinglot.model.payment.HourlyCost;
import com.java.ooad.parkinglot.model.payment.ParkingTicket;
import com.java.ooad.parkinglot.model.payment.TicketStatus;
import com.java.ooad.parkinglot.model.vehicle.Vehicle;
import com.java.ooad.parkinglot.model.vehicle.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static com.java.ooad.parkinglot.model.parking.spot.common.ParkingSpotType.*;


public class ParkingLotService {


    /**
     * @param vehicleType
     * @param parkingFloors
     * @return Optional<ParkingSpot>
     * <p>
     * finds and returns parking spot for given vehicle type searching all floors
     */
    public Optional<ParkingSpot> getParkingSpot(VehicleType vehicleType, List<ParkingFloor> parkingFloors) {
        Optional<ParkingSpot> parkingSpot = Optional.ofNullable(null);
        for (ParkingFloor parkingFloor : parkingFloors) {
            ParkingSpot spot = getSpotInFloor(vehicleType, parkingFloor);
            if (spot != null) {
                parkingSpot = Optional.ofNullable(spot);
            }
        }
        return parkingSpot;
    }

    /**
     * @param vehicleType
     * @param parkingFloors
     * @return boolean indicating if there is any parking spot free for given vehicleType
     */
    public boolean canPark(VehicleType vehicleType, List<ParkingFloor> parkingFloors) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            if (canParkOnFloor(getSpotTypeForVehicle(vehicleType), parkingFloor))
                return true;
        }
        return false;
    }

    /**
     * @param parkingFloors
     * @return if the parking lot is full
     */
    public boolean isFull(List<ParkingFloor> parkingFloors) {
        int index = 0;
        BitSet bitSet = new BitSet();
        for (ParkingFloor parkingFloor : parkingFloors) {
            bitSet.set(index++, isFloorFull(parkingFloor));
        }
        return bitSet.cardinality() == bitSet.size();
    }

    /**
     * @param parkingTicket
     * @param parkingFloors
     * @return parking ticket is updated with charges and spot vacated
     */
    public ParkingTicket scanAndVacate(ParkingTicket parkingTicket, List<ParkingFloor> parkingFloors) {
        ParkingSpot parkingSpot =
                vacateParkingSpot(parkingTicket.getAllocatedSpotId(), parkingFloors);
        parkingTicket.setCharges(calculateCost(parkingTicket, parkingSpot.getParkingSpotType()));
        return parkingTicket;
    }

    /**
     * @param vehicle
     * @param parkingFloors
     * @return a ticket with vehicle number and check in time
     */
    public Optional<ParkingTicket> issueParkingTicket(Vehicle vehicle, List<ParkingFloor> parkingFloors) {
        Optional<ParkingTicket> parkingTicket = Optional.ofNullable(null);
        if (canPark(vehicle.getType(), parkingFloors)) {
            Optional<ParkingSpot> parkingSpot = getParkingSpot(vehicle.getType(), parkingFloors);
            if (parkingSpot.isPresent()) {
                parkingTicket = buildTicket(vehicle.getLicenseNumber(), parkingSpot.get().getParkingSpotId());
            }
        }
        return parkingTicket;
    }

    private synchronized ParkingSpot getSpotInFloor(VehicleType vehicleType, ParkingFloor floor) {
        Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots = floor.getParkingSpots();
        Map<String, ParkingSpot> usedParkingSpots = floor.getUsedParkingSpots();
        /**Checks if the queue for the parking type has any spots at all*/
        if (!canParkOnFloor(getSpotTypeForVehicle(vehicleType), floor))
            return null;

        ParkingSpotType parkingSpotType = getSpotTypeForVehicle(vehicleType);
        ParkingSpot parkingSpot = parkingSpots.get(parkingSpotType).poll();

        usedParkingSpots.put(parkingSpot.getParkingSpotId(), parkingSpot);
        return parkingSpot;
    }

    private boolean canParkOnFloor(ParkingSpotType parkingSpotType, ParkingFloor floor) {
        Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots = floor.getParkingSpots();
        return parkingSpots.get(parkingSpotType).size() > 0;
    }

    private ParkingSpot vacateParkingSpot(String parkingSpotId, List<ParkingFloor> parkingFloors) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            ParkingSpot parkingSpot = vacateSpot(parkingSpotId, parkingFloor);
            if (parkingSpot != null)
                return parkingSpot;
        }
        return null;
    }

    private boolean isFloorFull(ParkingFloor parkingFloor) {
        Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots = parkingFloor.getParkingSpots();
        BitSet fullBitSet = new BitSet();
        int bitIndex = 0;
        for (Map.Entry<ParkingSpotType, Deque<ParkingSpot>> entry : parkingSpots.entrySet()) {
            if (entry.getValue().size() == 0) {
                fullBitSet.set(bitIndex++);
            } else {
                break;
            }
        }
        return fullBitSet.cardinality() == fullBitSet.size();
    }

    private static ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return CAR;
            case ELECTRIC:
                return ELECTRIC;
            case MOTORBIKE:
                return MOTORBIKE;
            default:
                return LARGE;
        }
    }

    private ParkingSpot vacateSpot(String parkingSpotId, ParkingFloor floor) {
        Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots = floor.getParkingSpots();
        Map<String, ParkingSpot> usedParkingSpots = floor.getUsedParkingSpots();
        ParkingSpot parkingSpot = usedParkingSpots.remove(parkingSpotId);
        if (parkingSpot != null) {
            parkingSpot.freeSpot();
            parkingSpots.get(parkingSpot.getParkingSpotType())
                    .addFirst(parkingSpot);
            return parkingSpot;
        }
        return null;
    }

    private Optional<ParkingTicket> buildTicket(String vehicleLicenseNumber, String parkingSpotId) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setAllocatedSpotId(parkingSpotId);
        parkingTicket.setLicensePlateNumber(vehicleLicenseNumber);
        parkingTicket.setTicketNumber(UUID.randomUUID().toString());
        parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
        return Optional.of(parkingTicket);
    }

    private double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
        long hours = duration.toHours();
        if (hours == 0)
            hours = 1;
        double amount = hours * new HourlyCost().getCost(parkingSpotType);
        return amount;
    }
}
