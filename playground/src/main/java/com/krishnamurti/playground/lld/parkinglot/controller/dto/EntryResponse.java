package com.krishnamurti.playground.lld.parkinglot.controller.dto;

import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;

/**
 * Data Transfer Object (DTO) representing the response when a vehicle requests entry.
 * It contains the assigned ticket if successful, the status of the request, and a descriptive message.
 */
public class EntryResponse {

    /**
     * Enum representing the possible status of a vehicle entry request.
     */
    public enum Status {
        ALLOTTED, NOT_ALLOTTED;
    }

    // The parking ticket issued upon successful entry.
    private Ticket ticket;
    
    // A message explaining the outcome of the entry request.
    private String message;
    
    // The current status of the entry request.
    private Status status;

    /**
     * Constructs an EntryResponse with the specified ticket, status, and message.
     *
     * @param ticket the ticket issued
     * @param status the outcome of the entry request
     * @param message a descriptive message
     */
    public EntryResponse(final Ticket ticket, final Status status, final String message) {
        this.ticket = ticket;
        this.status = status;
        this.message = message;
    }

    /**
     * Gets the status of the entry request.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the ticket issued for entry.
     *
     * @return the ticket
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * Gets the descriptive message associated with the response.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
