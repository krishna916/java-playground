package com.krishnamurti.playground.lld.parkinglot.repository;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Ticket data access.
 * Defines operations for generating and retrieving parking tickets.
 */
public interface TicketRepository {

    /**
     * Generates and persists a new parking ticket.
     *
     * @param time The entry time for the ticket.
     * @param vehicle The vehicle for which the ticket is generated.
     * @param slot The parking slot allocated to the vehicle.
     * @param rule The pricing rule applicable to this ticket.
     * @return The generated and persisted Ticket.
     */
    Ticket generateTicket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot, PricingRule rule);

    /**
     * Fetches a ticket by its unique identifier.
     *
     * @param ticketId The UUID of the ticket to retrieve.
     * @return An Optional containing the Ticket if found, or empty if not.
     */
    Optional<Ticket> fetchById(UUID ticketId);
}
