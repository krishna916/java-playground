package com.krishnamurti.playground.lld.parkinglot.service.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;
import com.krishnamurti.playground.lld.parkinglot.repository.PricingRuleRepository;
import com.krishnamurti.playground.lld.parkinglot.repository.TicketRepository;
import com.krishnamurti.playground.lld.parkinglot.service.TicketService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link TicketService}.
 * Provides functionality to generate and manage parking tickets by interacting with the persistence layer.
 */
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PricingRuleRepository pricingRuleRepository;

    public TicketServiceImpl(final TicketRepository ticketRepository, final PricingRuleRepository pricingRuleRepository) {
        this.ticketRepository = ticketRepository;
        this.pricingRuleRepository = pricingRuleRepository;
    }

    /**
     * {@inheritDoc}
     * This implementation fetches the system's default pricing rule to associate with the new ticket.
     */
    @Override
    public Ticket generateTicket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot) {
        // Business Logic: Always use the default pricing rule configured in the system for new tickets.
        return ticketRepository.generateTicket(time, vehicle, slot, pricingRuleRepository.fetchDefault());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Ticket> getById(UUID ticketId) {
        return ticketRepository.fetchById(ticketId);
    }
}
