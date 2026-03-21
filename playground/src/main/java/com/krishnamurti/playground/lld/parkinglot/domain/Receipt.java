package com.krishnamurti.playground.lld.parkinglot.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Receipt {

    private UUID receiptId;
    private Ticket ticket;
    private LocalDateTime exitTime;
    private double fee;
    private Payment payment;

    public Receipt(Ticket ticket, LocalDateTime exitTime, double fee, Payment payment) {
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.fee = fee;
        this.payment = payment;
        this.receiptId = UUID.randomUUID();
    }

    public UUID getReceiptId() {
        return receiptId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getFee() {
        return fee;
    }

    public Payment getPayment() {
        return payment;
    }
}
