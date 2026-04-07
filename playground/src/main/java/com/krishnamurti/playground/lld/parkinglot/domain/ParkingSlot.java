package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.UUID;

/**
 * Represents an individual parking slot within the parking lot.
 * A slot has a specific type (e.g., CAR, BIKE) and belongs to a specific floor.
 */
public class ParkingSlot {

    // Unique identifier for the parking slot
    private UUID parkingSlotId;
    
    // The type of vehicle this slot is designed for
    private SlotType type;
    
    // Flag indicating whether the slot is currently occupied by a vehicle
    private boolean isOccupied;
    
    // The floor where this parking slot is located
    private Floor floor;

    /**
     * Constructs a ParkingSlot with a specified type.
     *
     * @param type The type of slot (CAR, BIKE, TRUCK).
     */
    public ParkingSlot(SlotType type) {
        this.type = type;
        parkingSlotId = UUID.randomUUID();
    }

    /**
     * Constructs a ParkingSlot with a specified type and floor assignment.
     *
     * @param type  The type of slot.
     * @param floor The floor where the slot is situated.
     */
    public ParkingSlot(final SlotType type, final Floor floor) {
        this(type);
        this.floor = floor;
    }


    /**
     * @return The unique slot identifier.
     */
    public UUID getParkingSlotId() {
        return parkingSlotId;
    }

    /**
     * @return The type of the slot.
     */
    public SlotType getType() {
        return type;
    }

    /**
     * @return true if a vehicle is currently parked in this slot, false otherwise.
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Updates the occupancy status of the slot.
     *
     * @param occupied true to mark as occupied, false to mark as free.
     */
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    /**
     * @return The floor where this slot is located.
     */
    public Floor getFloor() {
        return floor;
    }
}
