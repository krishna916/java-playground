package com.krishnamurti.playground.lld.parkinglot.controller;


import com.krishnamurti.playground.lld.parkinglot.controller.dto.EntryResponse;
import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;
import com.krishnamurti.playground.lld.parkinglot.service.ParkingSlotService;
import com.krishnamurti.playground.lld.parkinglot.service.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController(Constants.PARKING_LOT)
public class EntryController {

    private final ParkingSlotService parkingSlotService;
    private final TicketService ticketService;

    public EntryController(final ParkingSlotService parkingSlotService, final TicketService ticketService) {
        this.parkingSlotService = parkingSlotService;
        this.ticketService = ticketService;
    }

    @PostMapping(Constants.ENTRY)
    public EntryResponse createVehicleEntry(String licensePlate, SlotType type) {
        System.out.println("[Controller]  vehicle type " + type.name() + " asking entry");
        Vehicle vehicle = new Vehicle(licensePlate, type);
        Optional<ParkingSlot> parkingSlot = parkingSlotService.occupyParkingSlot(type);

        if (parkingSlot.isEmpty()) {
            return new EntryResponse(null, EntryResponse.Status.NOT_ALLOTTED,"No Parking Slot available!!!");
        }

        Ticket ticket = ticketService.generateTicket(LocalDateTime.now(), vehicle, parkingSlot.get());

        System.out.println("[Controller] entry successful");
        return new EntryResponse(ticket, EntryResponse.Status.ALLOTTED, "Parking Slot Allotted");
    }

}
