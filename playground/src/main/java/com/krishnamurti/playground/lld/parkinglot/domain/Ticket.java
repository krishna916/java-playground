package com.krishnamurti.playground.lld.parkinglot.domain;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a parking ticket issued when a vehicle enters the parking lot.
 * It serves as a proof of entry and contains all necessary information to calculate
 * the parking fee upon exit.
 */
public class Ticket {

    // Unique identifier for the ticket
    private UUID ticketId;
    
    // The date and time when the vehicle entered the parking lot
    private LocalDateTime time;
    
    // The vehicle associated with this ticket
    private Vehicle vehicle;
    
    // The specific parking slot assigned to the vehicle
    private ParkingSlot slot;
    
    // The pricing rule applicable at the time of entry, used for fee calculation
    private PricingRule pricingRule;

    /**
     * Constructs a Ticket with the entry details.
     *
     * @param time        The entry timestamp.
     * @param vehicle     The vehicle being parked.
     * @param slot        The assigned parking slot.
     * @param rule        The pricing rule to be applied.
     */
    public Ticket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot, PricingRule rule) {
        this.time = time;
        this.vehicle = vehicle;
        this.slot = slot;
        this.pricingRule = rule;
        ticketId = UUID.randomUUID();
    }

    /**
     * @return The unique ticket identifier.
     */
    public UUID getTicketId() {
        return ticketId;
    }

    /**
     * @return The entry timestamp.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @return The vehicle associated with this ticket.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @return The parking slot assigned to the vehicle.
     */
    public ParkingSlot getSlot() {
        return slot;
    }

    /**
     * @return The pricing rule that was active when the ticket was issued.
     */
    public PricingRule getPricingRule() {
        return pricingRule;
    }
}
