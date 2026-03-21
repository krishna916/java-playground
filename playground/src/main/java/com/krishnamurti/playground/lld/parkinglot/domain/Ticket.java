package com.krishnamurti.playground.lld.parkinglot.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {

    private UUID ticketId;
    private LocalDateTime time;
    private Vehicle vehicle;
    private ParkingSlot slot;
    private PricingRule pricingRule;

    public Ticket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot, PricingRule rule) {
        this.time = time;
        this.vehicle = vehicle;
        this.slot = slot;
        this.pricingRule = rule;
        ticketId = UUID.randomUUID();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public PricingRule getPricingRule() {
        return pricingRule;
    }
}
