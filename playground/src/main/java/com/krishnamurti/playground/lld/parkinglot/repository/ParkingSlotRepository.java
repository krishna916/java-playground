package com.krishnamurti.playground.lld.parkinglot.repository;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing ParkingSlot data access.
 * Defines operations for persisting, searching, allocating, and releasing parking slots.
 */
public interface ParkingSlotRepository {

    /**
     * Persists a parking slot.
     *
     * @param slot The ParkingSlot to save.
     * @return The UUID of the saved parking slot.
     */
    UUID save(ParkingSlot slot);

    /**
     * Retrieves all currently available parking slots of a specific type.
     *
     * @param type The SlotType to filter for.
     * @return A list of available ParkingSlot objects.
     */
    List<ParkingSlot> getAvailableParkingSlots(SlotType type);

    /**
     * Attempts to find and atomically allocate an available parking slot of a specific type.
     *
     * @param type The SlotType required.
     * @return An Optional containing the allocated ParkingSlot if found, or empty if no slot is available.
     */
    Optional<ParkingSlot> allocateParkingSlot(SlotType type);

    /**
     * Releases a parking slot, marking it as unoccupied.
     *
     * @param parkingSlotId The UUID of the parking slot to release.
     * @return true if the slot was successfully released, false otherwise.
     */
    boolean releaseParkingSlot(UUID parkingSlotId);

}
