package com.krishnamurti.playground.lld.parkinglot.controller;

import com.krishnamurti.playground.lld.parkinglot.controller.dto.ExitResponse;
import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.service.ParkingSlotService;
import com.krishnamurti.playground.lld.parkinglot.service.RecieptService;
import com.krishnamurti.playground.lld.parkinglot.service.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller responsible for handling vehicle exit from the parking lot.
 * It coordinates between ticket validation, parking slot release, and receipt generation.
 */
@RestController(Constants.PARKING_LOT)
public class ExitController {

    // Services required for handling the exit process.
    TicketService ticketService;
    ParkingSlotService parkingSlotService;
    RecieptService recieptService;

    /**
     * Constructs the ExitController with necessary services.
     *
     * @param ticketService service to manage parking tickets
     * @param parkingSlotService service to manage parking slots
     * @param recieptService service to generate receipts
     */
    public ExitController(TicketService ticketService, ParkingSlotService parkingSlotService, RecieptService recieptService) {
        this.ticketService = ticketService;
        this.parkingSlotService = parkingSlotService;
        this.recieptService = recieptService;
    }

    /**
     * Processes an exit request for a vehicle given its ticket ID.
     * The process includes validating the ticket, releasing the assigned slot, and generating a receipt.
     *
     * @param ticketId the unique identifier of the parking ticket
     * @return ExitResponse containing the exit time and receipt details
     * @throws IllegalStateException if there's a mismatch or failure in releasing the parking slot
     */
    @PostMapping(Constants.EXIT)
    public ExitResponse exitRequest(UUID ticketId) {
        System.out.println("[Controller] exit initiated" + ticketId);
        LocalDateTime exitTime = LocalDateTime.now();
        Optional<Ticket> optionalTicket = ticketService.getById(ticketId);

        // If ticket is not found, return an empty exit response.
        if (optionalTicket.isEmpty()) {
            return new ExitResponse(exitTime, null);
        }

        Ticket ticket = optionalTicket.get();

        // Release the parking slot associated with this ticket.
        boolean releasedSlot = parkingSlotService.releaseParkingSlot(ticket.getSlot());
        if (!releasedSlot) {
            throw  new IllegalStateException("Parking Slot mismatch or not found!!");
        }

        // Generate the final receipt for the customer.
        Receipt receipt = recieptService.generateReceipt(ticket, exitTime);
        System.out.println("[Controller] exit completed" + ticketId);
        return new ExitResponse(exitTime, receipt);
    }


}
