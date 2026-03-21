package com.krishnamurti.playground.lld.parkinglot.repository.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;
import com.krishnamurti.playground.lld.parkinglot.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class TicketInMemoryRepository implements TicketRepository {

    private static final Map<UUID, Ticket> ticketCache = new HashMap<>();

    @Override
    public Ticket generateTicket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot, PricingRule rule) {
        Ticket ticket = new Ticket(time, vehicle, slot, rule);
        ticketCache.put(ticket.getTicketId(), ticket);
        return  ticket;
    }

    @Override
    public Optional<Ticket> fetchById(UUID ticketId) {
        return Optional.ofNullable(ticketCache.get(ticketId));
    }
}
