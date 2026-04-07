package com.krishnamurti.playground.lld.parkinglot.service.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.repository.ParkingSlotRepository;
import com.krishnamurti.playground.lld.parkinglot.service.ParkingSlotService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link ParkingSlotService}.
 * Manages the lifecycle of parking slots, including their registration, allocation to vehicles,
 * and status updates when vehicles exit.
 */
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingSlotServiceImpl(final ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID save(ParkingSlot slot) {
        return parkingSlotRepository.save(slot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ParkingSlot> getAvailableParkingSpots(SlotType vehicleType) {
        // Query the repository for slots of the requested type that are currently free.
        return parkingSlotRepository.getAvailableParkingSlots(vehicleType);
    }

    /**
     * {@inheritDoc}
     * This method contains the business logic for slot allocation. It attempts to find
     * an available slot for the specific vehicle type and mark it as occupied.
     */
    @Override
    public Optional<ParkingSlot> occupyParkingSlot(SlotType vehicleType) {
        // Business logic: Atomically find and mark a slot as occupied to prevent double-booking.
        return parkingSlotRepository.allocateParkingSlot(vehicleType);
    }

    /**
     * {@inheritDoc}
     * Releases the slot and marks it as available for other vehicles.
     */
    @Override
    public boolean releaseParkingSlot(ParkingSlot slot) {
        // Business logic: Update slot status back to AVAILABLE.
        return parkingSlotRepository.releaseParkingSlot(slot.getParkingSlotId());
    }
}
