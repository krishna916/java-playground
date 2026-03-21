package com.krishnamurti.playground.lld.parkinglot.repository.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.repository.ParkingSlotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSlotInMemoryRepository implements ParkingSlotRepository {

    private static final Map<UUID, ParkingSlot> parkingSlotCache = new ConcurrentHashMap<>();
    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public UUID save(ParkingSlot slot) {
        parkingSlotCache.put(slot.getParkingSlotId(), slot);
        return slot.getParkingSlotId();
    }

    @Override
    public List<ParkingSlot> getAvailableParkingSlots(SlotType type) {
        List<ParkingSlot> availableSlots = new ArrayList<>();
        for (final ParkingSlot slot: parkingSlotCache.values()) {
            if (isAvailable(slot, type)) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }

    @Override
    public Optional<ParkingSlot> allocateParkingSlot(SlotType type) {
        ParkingSlot allocatedSlot = null;
        lock.lock();
        try {
            for (final ParkingSlot slot: parkingSlotCache.values()) {
                if (isAvailable(slot, type)) {
                    allocatedSlot = slot;
                    allocatedSlot.setOccupied(true);
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
        return Optional.ofNullable(allocatedSlot);
    }

    @Override
    public boolean releaseParkingSlot(UUID parkingSlotId) {
        if (!parkingSlotCache.containsKey(parkingSlotId)) {
            throw new IllegalArgumentException("invalid parking slot");
        }
        lock.lock();
        try {
            ParkingSlot slot = parkingSlotCache.get(parkingSlotId);
            slot.setOccupied(false);
            return true;
        } finally {
            lock.unlock();
        }
    }

    private boolean isAvailable(ParkingSlot slot, SlotType type) {
        return !slot.isOccupied() && slot.getType().willAccomadate(type);
    }
}
