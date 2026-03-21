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

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final PricingRuleRepository pricingRuleRepository;

    public TicketServiceImpl(final TicketRepository ticketRepository, final PricingRuleRepository pricingRuleRepository) {
        this.ticketRepository = ticketRepository;
        this.pricingRuleRepository = pricingRuleRepository;
    }

    @Override
    public Ticket generateTicket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot) {
        return ticketRepository.generateTicket(time, vehicle, slot, pricingRuleRepository.fetchDefault());
    }

    @Override
    public Optional<Ticket> getById(UUID ticketId) {
        return ticketRepository.fetchById(ticketId);
    }
}
