package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.List;
import java.util.UUID;

public class Floor {

    private UUID floorId;
    private List<ParkingSlot> slots;

    public Floor(List<ParkingSlot> slots) {
        this.slots = slots;
        floorId = UUID.randomUUID();
    }

    public UUID getFloorId() {
        return floorId;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }
}
