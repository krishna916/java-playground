package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface TicketService {

    Ticket generateTicket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot);

    Optional<Ticket> getById(UUID ticketId);
}
