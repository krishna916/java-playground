package com.krishnamurti.playground.lld.trafficlight.controller;

import com.krishnamurti.playground.lld.trafficlight.domain.Direction;
import com.krishnamurti.playground.lld.trafficlight.service.EmergencyRequestService;
import com.krishnamurti.playground.lld.trafficlight.service.impl.EmergencyRequestServiceImpl;

public class EmergencyController {

    private final EmergencyRequestService emergencyRequestService;

    public EmergencyController(EmergencyRequestService emergencyRequestService) {
        this.emergencyRequestService = emergencyRequestService;
    }

    public Integer emergencyRequest(Integer intersectionId, Direction direction, Integer duration) {
        return emergencyRequestService.createEmergencyRequest(intersectionId, direction, duration);
    }
}
