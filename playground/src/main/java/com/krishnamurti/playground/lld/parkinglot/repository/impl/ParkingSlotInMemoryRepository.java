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

/**
 * In-memory implementation of the ParkingSlotRepository.
 * Manages parking slot availability and allocation using thread-safe mechanisms.
 *
 * Design Consideration: Uses a ReentrantLock to ensure that slot allocation and release
 * operations are atomic, preventing multiple vehicles from being assigned the same slot simultaneously.
 */
public class ParkingSlotInMemoryRepository implements ParkingSlotRepository {

    // Thread-safe in-memory cache to store parking slots by their UUID.
    private static final Map<UUID, ParkingSlot> parkingSlotCache = new ConcurrentHashMap<>();

    // Lock used to synchronize access to slot allocation and release logic.
    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public UUID save(ParkingSlot slot) {
        // Persist the parking slot in the local cache.
        parkingSlotCache.put(slot.getParkingSlotId(), slot);
        return slot.getParkingSlotId();
    }

    @Override
    public List<ParkingSlot> getAvailableParkingSlots(SlotType type) {
        // Filter and return a list of all slots that are currently unoccupied and match the type.
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
        // Acquire lock to ensure atomic search and update of slot status.
        lock.lock();
        try {
            for (final ParkingSlot slot: parkingSlotCache.values()) {
                if (isAvailable(slot, type)) {
                    allocatedSlot = slot;
                    // Mark the slot as occupied immediately upon finding it.
                    allocatedSlot.setOccupied(true);
                    break;
                }
            }
        } finally {
            // Ensure the lock is always released.
            lock.unlock();
        }
        return Optional.ofNullable(allocatedSlot);
    }

    @Override
    public boolean releaseParkingSlot(UUID parkingSlotId) {
        if (!parkingSlotCache.containsKey(parkingSlotId)) {
            throw new IllegalArgumentException("invalid parking slot");
        }
        // Acquire lock to ensure atomic update of slot status.
        lock.lock();
        try {
            ParkingSlot slot = parkingSlotCache.get(parkingSlotId);
            // Mark the slot as available.
            slot.setOccupied(false);
            return true;
        } finally {
            // Ensure the lock is always released.
            lock.unlock();
        }
    }

    /**
     * Internal helper to check if a slot is unoccupied and compatible with the requested type.
     */
    private boolean isAvailable(ParkingSlot slot, SlotType type) {
        return !slot.isOccupied() && slot.getType().willAccomadate(type);
    }
}
