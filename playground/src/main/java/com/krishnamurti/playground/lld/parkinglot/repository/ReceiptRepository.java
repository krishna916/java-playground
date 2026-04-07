package com.krishnamurti.playground.lld.parkinglot.repository;

import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;

/**
 * Repository interface for managing Receipt data access.
 * Defines operations for persisting parking receipts.
 */
public interface ReceiptRepository {

    /**
     * Persists a newly generated receipt.
     *
     * @param receipt The Receipt object to be persisted.
     * @return The persisted Receipt.
     */
    Receipt generateReceipt(Receipt receipt);
}
