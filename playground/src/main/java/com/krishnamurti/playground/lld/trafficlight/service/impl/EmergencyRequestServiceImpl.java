package com.krishnamurti.playground.lld.trafficlight.service.impl;

import com.krishnamurti.playground.lld.trafficlight.domain.Direction;
import com.krishnamurti.playground.lld.trafficlight.domain.EmergencyRequest;
import com.krishnamurti.playground.lld.trafficlight.domain.Intersection;
import com.krishnamurti.playground.lld.trafficlight.domain.SignalCycle;
import com.krishnamurti.playground.lld.trafficlight.repository.EmergencyRequestRepository;
import com.krishnamurti.playground.lld.trafficlight.repository.IDGenerator;
import com.krishnamurti.playground.lld.trafficlight.service.EmergencyRequestService;
import com.krishnamurti.playground.lld.trafficlight.service.IntersectionService;

import java.util.Optional;

public class EmergencyRequestServiceImpl implements EmergencyRequestService {

    private final EmergencyRequestRepository emergencyRequestRepository;
    private final IntersectionService intersectionService;
    private final IDGenerator idGenerator;


    public EmergencyRequestServiceImpl(final IDGenerator idGenerator, final EmergencyRequestRepository emergencyRequestRepository, final IntersectionService intersectionService) {
        this.idGenerator = idGenerator;
        this.emergencyRequestRepository = emergencyRequestRepository;
        this.intersectionService = intersectionService;
    }

    @Override
    public Integer createEmergencyRequest(Integer intersectionId, Direction direction, Integer duration) {
        Intersection intersection = intersectionService.getById(intersectionId);
        if (!intersection.getSignalCycle().isRunning()) {
            throw new IllegalStateException("Traffic Light Cycle is inactive");
        }


        EmergencyRequest emergencyRequest = new EmergencyRequest(
                idGenerator.getNextId(EmergencyRequest.OBJECT_ID),
                intersectionId,
                direction,
                duration,
                true
        );

        intersection.setEmergencyMode(true);
        // pause the cycle
        intersection.handleEmergency(emergencyRequest);

        return 0;
    }
}
