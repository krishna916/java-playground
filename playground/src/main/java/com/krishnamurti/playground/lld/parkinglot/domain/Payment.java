package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.UUID;

public class Payment {

    public enum Status {
        PENDING, SUCCESS, FAILED;
    }

    private UUID paymentId;
    private double amount;
    private Status status;

    public Payment(double amount, Status status) {
        this.amount = amount;
        this.status = status;
        paymentId = UUID.randomUUID();
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }
}
