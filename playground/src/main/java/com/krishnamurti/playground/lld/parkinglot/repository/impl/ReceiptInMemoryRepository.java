package com.krishnamurti.playground.lld.parkinglot.repository.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.Receipt;
import com.krishnamurti.playground.lld.parkinglot.domain.Ticket;
import com.krishnamurti.playground.lld.parkinglot.repository.ReceiptRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ReceiptInMemoryRepository implements ReceiptRepository {

    private static final Map<UUID, Receipt> reciptCache = new ConcurrentHashMap<>();


    @Override
    public Receipt generateReceipt(Receipt receipt) {
        reciptCache.put(receipt.getReceiptId(), receipt); // In real scenario, it would be DB call
        return receipt;
    }
}
