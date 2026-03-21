package com.krishnamurti.playground.lld.parkinglot.repository;

import com.krishnamurti.playground.lld.parkinglot.domain.ParkingSlot;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.domain.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParkingSlotRepository {

    UUID save(ParkingSlot slot);

    List<ParkingSlot> getAvailableParkingSlots(SlotType type);

    Optional<ParkingSlot> allocateParkingSlot(SlotType type);

    boolean releaseParkingSlot(UUID parkingSlotId);

}
