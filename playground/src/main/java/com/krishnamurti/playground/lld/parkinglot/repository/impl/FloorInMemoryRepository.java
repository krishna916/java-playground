package com.krishnamurti.playground.lld.parkinglot.repository.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.Floor;
import com.krishnamurti.playground.lld.parkinglot.repository.FloorRepository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of the FloorRepository.
 * Manages parking floors in a thread-safe local cache.
 */
public class FloorInMemoryRepository implements FloorRepository {

    // Thread-safe in-memory cache to store parking floors by their UUID.
    private static final Map<UUID, Floor> floorCache = new ConcurrentHashMap<>();

    @Override
    public Floor addFloor(Floor floor) {
        // Store the floor in the local cache.
        floorCache.put(floor.getFloorId(), floor);
        return floor;
    }
}
