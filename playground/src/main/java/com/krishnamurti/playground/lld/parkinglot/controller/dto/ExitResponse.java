package com.krishnamurti.playground.lld.parkinglot.controller.dto;

import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing the response when a vehicle exits the parking lot.
 * It carries information about the time of exit and the generated receipt.
 */
public class ExitResponse {

    // The timestamp when the vehicle exited the parking lot.
    private LocalDateTime exitTime;

    // The receipt generated for the parking session, containing payment details.
    private Receipt receipt;

    /**
     * Constructs an ExitResponse with the specified exit time and receipt.
     *
     * @param exitTime the time of exit
     * @param receipt the receipt for the parking session
     */
    public ExitResponse(LocalDateTime exitTime, Receipt receipt) {
        this.exitTime = exitTime;
        this.receipt = receipt;
    }

    /**
     * Gets the exit time.
     *
     * @return the exit time
     */
    public LocalDateTime getExitTime() {
        return exitTime;
    }

    /**
     * Gets the receipt associated with the exit.
     *
     * @return the receipt
     */
    public Receipt getReceipt() {
        return receipt;
    }
}
