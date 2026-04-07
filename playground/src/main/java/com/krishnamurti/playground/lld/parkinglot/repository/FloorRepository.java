package com.krishnamurti.playground.lld.parkinglot.repository;

import com.krishnamurti.playground.lld.parkinglot.domain.Floor;

/**
 * Repository interface for managing Floor data access.
 * Defines operations for persisting parking floors.
 */
public interface FloorRepository {

    /**
     * Persists a new floor in the system.
     *
     * @param floor The Floor object to add.
     * @return The persisted Floor.
     */
    Floor addFloor(Floor floor);
}
