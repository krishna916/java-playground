package com.krishnamurti.playground.lld.trafficlight.controller;

import com.krishnamurti.playground.lld.trafficlight.domain.Direction;
import com.krishnamurti.playground.lld.trafficlight.service.IntersectionService;

import java.util.List;

public class IntersectionController {

    private final IntersectionService intersectionService;

    public IntersectionController(IntersectionService intersectionService) {
        this.intersectionService = intersectionService;
    }

    public int createIntersection() {
        return intersectionService.addIntersection(List.of(Direction.values()));
    }

    public boolean startCycle(Integer intersectionId) {
        return intersectionService.startCycle(intersectionId);
    }

    public void displayState(Integer intersectionId) {
        intersectionService.displayCurrentState(intersectionId);
    }
}
