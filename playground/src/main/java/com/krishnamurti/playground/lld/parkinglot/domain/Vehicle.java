package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.UUID;

/**
 * Represents a vehicle that enters the parking lot.
 * Each vehicle has a unique identifier, a license plate, and a specific type
 * which determines the kind of parking slot it requires.
 */
public class Vehicle {

    // Unique internal identifier for the vehicle
    private final UUID vehicleId;
    
    // The license plate number of the vehicle, used for identification at entry and exit
    private final String licensePlate;
    
    // The category of the vehicle (e.g., CAR, BIKE, TRUCK)
    private final SlotType type;

    /**
     * Constructs a new Vehicle with the specified license plate and type.
     * A unique UUID is automatically generated for each vehicle.
     *
     * @param licensePlate The license plate of the vehicle.
     * @param type         The type of the vehicle.
     */
    public Vehicle(final String licensePlate, final SlotType type) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.vehicleId = UUID.randomUUID();
    }

    /**
     * @return The unique identifier of the vehicle.
     */
    public UUID getVehicleId() {
        return vehicleId;
    }

    /**
     * @return The license plate of the vehicle.
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @return The type of the vehicle, determining its parking requirements.
     */
    public SlotType getType() {
        return type;
    }

}
