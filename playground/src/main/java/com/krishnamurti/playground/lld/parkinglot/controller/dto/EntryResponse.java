package com.krishnamurti.playground.lld.parkinglot.controller.dto;

import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;

public class EntryResponse {

    public enum Status {
        ALLOTTED, NOT_ALLOTTED;
    }

    private Ticket ticket;
    private String message;
    private Status status;

    public EntryResponse(final Ticket ticket, final Status status, final String message) {
        this.ticket = ticket;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getMessage() {
        return message;
    }
}
