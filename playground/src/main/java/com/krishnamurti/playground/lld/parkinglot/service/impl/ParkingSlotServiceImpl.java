package com.krishnamurti.playground.lld.parkinglot.service.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.repository.ParkingSlotRepository;
import com.krishnamurti.playground.lld.parkinglot.service.ParkingSlotService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingSlotServiceImpl(final ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Override
    public UUID save(ParkingSlot slot) {
        return parkingSlotRepository.save(slot);
    }

    @Override
    public List<ParkingSlot> getAvailableParkingSpots(SlotType vehicleType) {
        return parkingSlotRepository.getAvailableParkingSlots(vehicleType);
    }

    @Override
    public Optional<ParkingSlot> occupyParkingSlot(SlotType vehicleType) {
        return parkingSlotRepository.allocateParkingSlot(vehicleType);
    }

    @Override
    public boolean releaseParkingSlot(ParkingSlot slot) {
        return parkingSlotRepository.releaseParkingSlot(slot.getParkingSlotId());
    }
}
