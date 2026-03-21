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

@RestController(Constants.PARKING_LOT)
public class ExitController {

    TicketService ticketService;
    ParkingSlotService parkingSlotService;
    RecieptService recieptService;

    public ExitController(TicketService ticketService, ParkingSlotService parkingSlotService, RecieptService recieptService) {
        this.ticketService = ticketService;
        this.parkingSlotService = parkingSlotService;
        this.recieptService = recieptService;
    }

    @PostMapping(Constants.EXIT)
    public ExitResponse exitRequest(UUID ticketId) {
        System.out.println("[Controller] exit initiated" + ticketId);
        LocalDateTime exitTime = LocalDateTime.now();
        Optional<Ticket> optionalTicket = ticketService.getById(ticketId);

        if (optionalTicket.isEmpty()) {
            return new ExitResponse(exitTime, null);
        }

        Ticket ticket = optionalTicket.get();
        boolean releasedSlot = parkingSlotService.releaseParkingSlot(ticket.getSlot());
        if (!releasedSlot) {
            throw  new IllegalStateException("Parking Slot mismatch or not found!!");
        }

        Receipt receipt = recieptService.generateReceipt(ticket, exitTime);
        System.out.println("[Controller] exit completed" + ticketId);
        return new ExitResponse(exitTime, receipt);
    }


}
