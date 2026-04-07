package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing parking slots within the parking lot.
 * Handles slot registration, availability checks, and allocation/deallocation logic.
 */
public interface ParkingSlotService {

    /**
     * Saves a new parking slot configuration.
     *
     * @param slot The parking slot to save.
     * @return The UUID assigned to the saved slot.
     */
    UUID save(ParkingSlot slot);

    /**
     * Retrieves a list of all currently available parking spots for a specific vehicle type.
     *
     * @param vehicleType The type of slot (e.g., CAR, BIKE) to search for.
     * @return A list of available {@link ParkingSlot}s.
     */
    List<ParkingSlot> getAvailableParkingSpots(SlotType vehicleType);

    /**
     * Attempts to occupy/allocate a parking slot for a given vehicle type.
     *
     * @param vehicleType The type of vehicle requiring a slot.
     * @return An {@link Optional} containing the allocated slot if available, or empty otherwise.
     */
    Optional<ParkingSlot> occupyParkingSlot(SlotType vehicleType);

    /**
     * Releases an occupied parking slot, making it available for future use.
     *
     * @param slot The parking slot to be released.
     * @return true if the slot was successfully released, false otherwise.
     */
    boolean releaseParkingSlot(ParkingSlot slot);
}
