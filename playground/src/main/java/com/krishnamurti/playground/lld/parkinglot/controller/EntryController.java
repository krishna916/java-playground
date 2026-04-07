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

/**
 * Controller responsible for managing vehicle entry into the parking lot.
 * It handles checking slot availability, occupying slots, and issuing tickets.
 */
@RestController(Constants.PARKING_LOT)
public class EntryController {

    // Services required for handling vehicle entry.
    private final ParkingSlotService parkingSlotService;
    private final TicketService ticketService;

    /**
     * Constructs the EntryController with necessary services.
     *
     * @param parkingSlotService service to manage parking slots
     * @param ticketService service to manage tickets
     */
    public EntryController(final ParkingSlotService parkingSlotService, final TicketService ticketService) {
        this.parkingSlotService = parkingSlotService;
        this.ticketService = ticketService;
    }

    /**
     * Creates an entry for a vehicle if a parking slot is available.
     * The process involves creating a vehicle object, checking for an available slot of the requested type,
     * occupying that slot, and then generating a ticket.
     *
     * @param licensePlate the license plate of the vehicle
     * @param type the type of slot required (e.g., CAR, BIKE, TRUCK)
     * @return EntryResponse containing the entry result (success or failure) and ticket if applicable
     */
    @PostMapping(Constants.ENTRY)
    public EntryResponse createVehicleEntry(String licensePlate, SlotType type) {
        System.out.println("[Controller]  vehicle type " + type.name() + " asking entry");
        
        // Prepare vehicle information for internal use.
        Vehicle vehicle = new Vehicle(licensePlate, type);
        
        // Attempt to find and occupy a slot of the specified type.
        Optional<ParkingSlot> parkingSlot = parkingSlotService.occupyParkingSlot(type);

        // If no slot is available, return a response with 'NOT_ALLOTTED' status.
        if (parkingSlot.isEmpty()) {
            return new EntryResponse(null, EntryResponse.Status.NOT_ALLOTTED,"No Parking Slot available!!!");
        }

        // Successfully occupied a slot, now generate and return a ticket.
        Ticket ticket = ticketService.generateTicket(LocalDateTime.now(), vehicle, parkingSlot.get());

        System.out.println("[Controller] entry successful");
        return new EntryResponse(ticket, EntryResponse.Status.ALLOTTED, "Parking Slot Allotted");
    }

}
