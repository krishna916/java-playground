package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.UUID;

public class Vehicle {

    private final UUID vehicleId;
    private final String licensePlate;
    private final SlotType type;

    public Vehicle(final String licensePlate, final SlotType type) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.vehicleId = UUID.randomUUID();
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public SlotType getType() {
        return type;
    }

}
