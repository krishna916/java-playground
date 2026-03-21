package com.krishnamurti.playground.lld.parkinglot.service.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.Floor;
import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.repository.FloorRepository;
import com.krishnamurti.playground.lld.parkinglot.service.FloorService;
import com.krishnamurti.playground.lld.parkinglot.service.ParkingSlotService;

public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;
    private final ParkingSlotService parkingSlotService;

    public FloorServiceImpl(FloorRepository floorRepository, ParkingSlotService parkingSlotService) {
        this.floorRepository = floorRepository;
        this.parkingSlotService = parkingSlotService;
    }

    @Override
    public Floor addFloor(Floor floor) {
        if (floor == null || floor.getSlots() == null || floor.getSlots().isEmpty()) {
            throw new IllegalArgumentException("Invalid floor configuration");
        }

        for (final ParkingSlot slot: floor.getSlots()) {
            parkingSlotService.save(slot);
        }
        return floorRepository.addFloor(floor);
    }
}
