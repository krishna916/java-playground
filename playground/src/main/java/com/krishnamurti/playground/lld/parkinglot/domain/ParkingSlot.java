package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.UUID;

public class ParkingSlot {


    private UUID parkingSlotId;
    private SlotType type;
    private boolean isOccupied;
    private Floor floor;

    public ParkingSlot(SlotType type) {
        this.type = type;
        parkingSlotId = UUID.randomUUID();
    }

    public ParkingSlot(final SlotType type, final Floor floor) {
        this(type);
        this.floor = floor;
    }


    public UUID getParkingSlotId() {
        return parkingSlotId;
    }

    public SlotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Floor getFloor() {
        return floor;
    }
}
