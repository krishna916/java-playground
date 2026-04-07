package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.Floor;

/**
 * Service interface for managing parking lot floors.
 * Responsible for floor initialization and configuration.
 */
public interface FloorService {
    /**
     * Adds a new floor to the parking lot.
     * This includes registering all slots associated with the floor.
     *
     * @param floor The floor entity to be added.
     * @return The added {@link Floor} entity.
     */
    Floor addFloor(Floor floor);
}
