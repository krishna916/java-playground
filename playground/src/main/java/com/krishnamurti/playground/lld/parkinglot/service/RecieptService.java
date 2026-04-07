package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
 * Service interface for generating receipts upon vehicle exit.
 * Responsible for calculating fees and processing payments.
 */
public interface RecieptService {
    /**
     * Generates a receipt for a given ticket at the time of exit.
     * This involves calculating the duration stayed, applying pricing rules,
     * and recording the payment.
     *
     * @param ticket   The original parking ticket issued at entry.
     * @param exitTime The time at which the vehicle is exiting.
     * @return A {@link Receipt} containing fee details and payment confirmation.
     */
    Receipt generateReceipt(Ticket ticket, LocalDateTime exitTime);
}
