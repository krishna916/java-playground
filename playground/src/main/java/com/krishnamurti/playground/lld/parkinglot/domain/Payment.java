package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.UUID;

/**
 * Represents a payment transaction for a parking session.
 * It tracks the amount paid and the current status of the transaction.
 */
public class Payment {

    /**
     * Possible states of a payment transaction.
     */
    public enum Status {
        // Payment is initiated but not yet finalized
        PENDING, 
        // Payment has been successfully processed
        SUCCESS, 
        // Payment processing failed
        FAILED;
    }

    // Unique identifier for the payment transaction
    private UUID paymentId;
    
    // The amount of money involved in the transaction
    private double amount;
    
    // The current status of the payment
    private Status status;

    /**
     * Constructs a Payment record.
     *
     * @param amount The amount to be paid.
     * @param status The initial status of the payment.
     */
    public Payment(double amount, Status status) {
        this.amount = amount;
        this.status = status;
        paymentId = UUID.randomUUID();
    }

    /**
     * @return The unique payment identifier.
     */
    public UUID getPaymentId() {
        return paymentId;
    }

    /**
     * @return The amount paid or to be paid.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return The current status of the payment.
     */
    public Status getStatus() {
        return status;
    }
}
