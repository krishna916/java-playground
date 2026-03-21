package com.krishnamurti.playground.lld.parkinglot.repository.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.Floor;
import com.krishnamurti.playground.lld.parkinglot.repository.FloorRepository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FloorInMemoryRepository implements FloorRepository {

    private static final Map<UUID, Floor> floorCache = new ConcurrentHashMap<>();

    @Override
    public Floor addFloor(Floor floor) {
        floorCache.put(floor.getFloorId(), floor);
        return floor;
    }
}
