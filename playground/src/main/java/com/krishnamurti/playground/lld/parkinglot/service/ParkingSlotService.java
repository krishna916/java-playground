package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParkingSlotService {

    UUID save(ParkingSlot slot);

    List<ParkingSlot> getAvailableParkingSpots(SlotType vehicleType);

    Optional<ParkingSlot> occupyParkingSlot(SlotType vehicleType);

    boolean releaseParkingSlot(ParkingSlot slot);
}
