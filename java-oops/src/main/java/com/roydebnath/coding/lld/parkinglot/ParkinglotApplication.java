package com.roydebnath.coding.lld.parkinglot;

import com.roydebnath.coding.lld.parkinglot.exceptions.InvlaidParkingFloorException;
import com.roydebnath.coding.lld.parkinglot.model.parking.ParkingFloor;
import com.roydebnath.coding.lld.parkinglot.model.parking.ParkingLot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.CarParkingSpot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.MotorBikeParkingSpot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpot;
import com.roydebnath.coding.lld.parkinglot.model.parking.spot.common.ParkingSpotType;
import com.roydebnath.coding.lld.parkinglot.model.payment.ParkingTicket;
import com.roydebnath.coding.lld.parkinglot.model.payment.Payment;
import com.roydebnath.coding.lld.parkinglot.model.vehicle.*;
import com.roydebnath.coding.lld.parkinglot.services.AdminService;
import com.roydebnath.coding.lld.parkinglot.services.ParkingLotService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ParkinglotApplication {

    public static void main(String[] args) throws InvlaidParkingFloorException {

        /**
         * Get the parking lot singleton
         */
        ParkingLot parkingLot = ParkingLot.INSTANCE;

        /**
         * Initialize The AdminService and ParkingLotService
         */
        AdminService adminService = new AdminService();
        ParkingLotService parkingLotService = new ParkingLotService();

        /**
         * Add Parking floors to the Parking Lot (Singleton)
         */
        adminService.addParkingFloor(new ParkingFloor("1"));
        adminService.addParkingFloor(new ParkingFloor("2"));
        

        /**
         * Creating Different type of Parking Spots and adding them to the floor 1
         */
        String floorId_1 = parkingLot.getParkingFloors().stream().filter(floor -> floor.getFloorId().equals("1")).findFirst().get().getFloorId();
        
       /** Created Two car spot and one bike spot*/
        ParkingSpot carSpot1 = new CarParkingSpot("c1");
        ParkingSpot carSpot2 = new CarParkingSpot("c2");
        ParkingSpot bikeSpot = new MotorBikeParkingSpot("b1");

        /** Added the newly created spots to floor 1*/
        adminService.addParkingSpot(floorId_1, carSpot1);
        adminService.addParkingSpot(floorId_1, bikeSpot);
        adminService.addParkingSpot(floorId_1, carSpot2);

        List<ParkingFloor> parkingFloors = ParkingLot.INSTANCE.getParkingFloors();

        System.out.println("Availability of CAR parking : " + parkingLotService.canPark(VehicleType.CAR, parkingFloors));
        System.out.println("Availability of EBIKE parking : " + parkingLotService.canPark(VehicleType.EBIKE, parkingFloors));
        System.out.println("Availability of ELECTRIC parking : " + parkingLotService.canPark(VehicleType.ELECTRIC, parkingFloors));

        System.out.println("Is Parking full in all floors : " + parkingLotService.isFull(parkingFloors));

        /** Get Parking Spot for a car */
        Vehicle car1 = new Car("KA05MR2311");
        Optional<ParkingSpot> carParkingSpotOptional = parkingLotService.getParkingSpot(car1.getType(), parkingFloors);
        carParkingSpotOptional.ifPresent(parkingSpot -> {
            System.out.println("Type of Parking Spot Found : " + parkingSpot.getParkingSpotType());
            System.out.println("Parking Spot Id : " + parkingSpot.getParkingSpotId());
        });


        /** Cannot Get Parking Spot for a VAN */
        Vehicle van = new Van("KA01MR7804");
        Optional<ParkingSpot> vanParkingSpotOptional = parkingLotService.getParkingSpot(van.getType(), parkingFloors);
        if (vanParkingSpotOptional.isPresent()){
            System.out.println("VAN spot is available");
        }else {
            System.out.println("VAN spot is not available");
        }



        /** Get Parking Ticket for the Car */
        Optional<ParkingTicket> parkingTicket = parkingLotService.issueParkingTicket(car1, parkingFloors);
        parkingTicket.ifPresent(ticket -> System.out.println(ticket.getAllocatedSpotId()));

        /** After adding one more car spot we should be able to park and get ticket for a car */
        adminService.addParkingSpot(floorId_1, carSpot1);
        Vehicle car = new Car("KA02MR6355");
        Optional<ParkingTicket> parkingTicket1 = parkingLotService.issueParkingTicket(car, parkingFloors);
        parkingTicket1.ifPresent(ticket -> System.out.println(ticket.getAllocatedSpotId()));

        /** No parking ticket should be issued as no spot is free */
        Optional<ParkingTicket> parkingTicket2 = parkingLotService.issueParkingTicket(new Car("ka04rb8458"), parkingFloors);
        if (parkingTicket2.isPresent()){
            System.out.println("" + parkingTicket2.get().getAllocatedSpotId());
        }else {
            System.out.println("Ticket could not be issued");
        }

        /** Parking ticket should be issued for Motorbike */
        Optional<ParkingTicket> bikeTicket = parkingLotService.issueParkingTicket(new MotorBike("ka01ee4901"), parkingFloors);
        bikeTicket.ifPresent(ticket -> System.out.println(ticket.getAllocatedSpotId()));


        /** Get ticket detail */
        ParkingTicket ticketDone = parkingLotService.scanAndVacate(bikeTicket.get(), parkingFloors);
        System.out.println("Ticket Charges : " + ticketDone.getCharges());

        /** Park on vacated spot */
        Optional<ParkingTicket> parkingTicket3 = parkingLotService.issueParkingTicket(new MotorBike("ka01ee7791"), parkingFloors);
        parkingTicket3.ifPresent(ticket -> System.out.println(ticket.getAllocatedSpotId()));


        /** Get spot count */
        System.out.println(parkingFloors.get(0).getParkingSpots().get(ParkingSpotType.CAR).size());

        /** Make Payment */
        ParkingTicket ticket = parkingTicket1.get();
        Payment payment = new Payment(UUID.randomUUID().toString(),  ticket.getTicketNumber(), ticket.getCharges());
        payment.makePayment();
        System.out.println("Payment Status : " + payment.getPaymentStatus());

    }
}
