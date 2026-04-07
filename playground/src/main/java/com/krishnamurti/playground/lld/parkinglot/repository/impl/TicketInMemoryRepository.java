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

/**
 * In-memory implementation of the TicketRepository.
 * Stores parking tickets in a local cache for fast access.
 *
 * Design Consideration: This implementation uses a standard HashMap, which is not thread-safe.
 * For concurrent environments, a ConcurrentHashMap or external synchronization may be required.
 */
public class TicketInMemoryRepository implements TicketRepository {

    // Simple in-memory cache to store tickets by their UUID.
    private static final Map<UUID, Ticket> ticketCache = new HashMap<>();

    @Override
    public Ticket generateTicket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot, PricingRule rule) {
        // Create a new Ticket instance and store it in the cache.
        Ticket ticket = new Ticket(time, vehicle, slot, rule);
        ticketCache.put(ticket.getTicketId(), ticket);
        return  ticket;
    }

    @Override
    public Optional<Ticket> fetchById(UUID ticketId) {
        // Retrieve the ticket from the cache if it exists.
        return Optional.ofNullable(ticketCache.get(ticketId));
    }
}
