package com.krishnamurti.playground.lld.parkinglot.repository;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository {

    Ticket generateTicket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot, PricingRule rule);

    Optional<Ticket> fetchById(UUID ticketId);
}
