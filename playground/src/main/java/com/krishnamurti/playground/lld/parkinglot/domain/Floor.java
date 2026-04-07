package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.List;
import java.util.UUID;

/**
 * Represents a floor in the parking lot.
 * Each floor contains a collection of parking slots of various types.
 */
public class Floor {

    // Unique identifier for the floor
    private UUID floorId;
    
    // List of all parking slots available on this floor
    private List<ParkingSlot> slots;

    /**
     * Constructs a Floor with a given list of parking slots.
     *
     * @param slots The list of slots that belong to this floor.
     */
    public Floor(List<ParkingSlot> slots) {
        this.slots = slots;
        floorId = UUID.randomUUID();
    }

    /**
     * @return The unique floor identifier.
     */
    public UUID getFloorId() {
        return floorId;
    }

    /**
     * @return The list of parking slots on this floor.
     */
    public List<ParkingSlot> getSlots() {
        return slots;
    }
}
