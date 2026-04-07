package com.krishnamurti.playground.lld.parkinglot.service.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.Floor;
import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.repository.FloorRepository;
import com.krishnamurti.playground.lld.parkinglot.service.FloorService;
import com.krishnamurti.playground.lld.parkinglot.service.ParkingSlotService;

/**
 * Implementation of {@link FloorService}.
 * Handles the registration of new floors and ensures that all associated slots are correctly initialized.
 */
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;
    private final ParkingSlotService parkingSlotService;

    public FloorServiceImpl(FloorRepository floorRepository, ParkingSlotService parkingSlotService) {
        this.floorRepository = floorRepository;
        this.parkingSlotService = parkingSlotService;
    }

    /**
     * {@inheritDoc}
     * This implementation performs validation checks before persisting the floor.
     * It also ensures that all parking slots associated with this floor are saved
     * individually before the floor entity is registered.
     */
    @Override
    public Floor addFloor(Floor floor) {
        // Business logic: Ensure floor configuration is valid.
        if (floor == null || floor.getSlots() == null || floor.getSlots().isEmpty()) {
            throw new IllegalArgumentException("Invalid floor configuration");
        }

        // Business logic: Each slot on the floor must be individually registered in the system.
        for (final ParkingSlot slot: floor.getSlots()) {
            parkingSlotService.save(slot);
        }

        // Persist the floor configuration.
        return floorRepository.addFloor(floor);
    }
}
