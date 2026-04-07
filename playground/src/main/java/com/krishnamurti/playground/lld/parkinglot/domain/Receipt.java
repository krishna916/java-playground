package com.krishnamurti.playground.lld.parkinglot.domain;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a receipt issued after a successful payment when a vehicle exits.
 * It links the original ticket with the final payment and timing details.
 */
public class Receipt {

    // Unique identifier for the receipt
    private UUID receiptId;
    
    // The ticket that was settled
    private Ticket ticket;
    
    // The date and time when the vehicle exited
    private LocalDateTime exitTime;
    
    // The total fee calculated and paid
    private double fee;
    
    // Details of the payment transaction
    private Payment payment;

    /**
     * Constructs a Receipt with the final exit and payment details.
     *
     * @param ticket   The original parking ticket.
     * @param exitTime The time of exit.
     * @param fee      The total amount charged.
     * @param payment  The payment record.
     */
    public Receipt(Ticket ticket, LocalDateTime exitTime, double fee, Payment payment) {
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.fee = fee;
        this.payment = payment;
        this.receiptId = UUID.randomUUID();
    }

    /**
     * @return The unique receipt identifier.
     */
    public UUID getReceiptId() {
        return receiptId;
    }

    /**
     * @return The ticket associated with this receipt.
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * @return The exit timestamp.
     */
    public LocalDateTime getExitTime() {
        return exitTime;
    }

    /**
     * @return The total fee paid.
     */
    public double getFee() {
        return fee;
    }

    /**
     * @return The payment details.
     */
    public Payment getPayment() {
        return payment;
    }
}
