package com.krishnamurti.playground.lld.parkinglot.repository.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.repository.ReceiptRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of the ReceiptRepository.
 * Stores parking receipts in a thread-safe local cache for fast access.
 */
public class ReceiptInMemoryRepository implements ReceiptRepository {

    // Thread-safe in-memory cache to store receipts by their UUID.
    private static final Map<UUID, Receipt> reciptCache = new ConcurrentHashMap<>();


    @Override
    public Receipt generateReceipt(Receipt receipt) {
        // Persist the receipt in the in-memory cache.
        // In a real scenario, this would be a database call.
        reciptCache.put(receipt.getReceiptId(), receipt);
        return receipt;
    }
}
