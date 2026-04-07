package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing parking tickets.
 * Handles the generation and retrieval of tickets for vehicles entering the parking lot.
 */
public interface TicketService {

    /**
     * Generates a new parking ticket for a vehicle.
     *
     * @param time    The entry time of the vehicle.
     * @param vehicle The vehicle for which the ticket is being generated.
     * @param slot    The parking slot allocated to the vehicle.
     * @return A {@link Ticket} object containing entry details and the applicable pricing rule.
     */
    Ticket generateTicket(LocalDateTime time, Vehicle vehicle, ParkingSlot slot);

    /**
     * Retrieves a ticket by its unique identifier.
     *
     * @param ticketId The UUID of the ticket.
     * @return An {@link Optional} containing the ticket if found, or empty otherwise.
     */
    Optional<Ticket> getById(UUID ticketId);
}
