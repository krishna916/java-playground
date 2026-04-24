package com.krishnamurti.playground.lld.trafficlight;

import com.krishnamurti.playground.lld.trafficlight.controller.EmergencyController;
import com.krishnamurti.playground.lld.trafficlight.controller.IntersectionController;
import com.krishnamurti.playground.lld.trafficlight.domain.Direction;
import com.krishnamurti.playground.lld.trafficlight.repository.EmergencyRequestRepository;
import com.krishnamurti.playground.lld.trafficlight.repository.IDGenerator;
import com.krishnamurti.playground.lld.trafficlight.repository.IntersectionRepository;
import com.krishnamurti.playground.lld.trafficlight.repository.cacheimpl.EmergencyRequestInMemoryRepository;
import com.krishnamurti.playground.lld.trafficlight.repository.cacheimpl.InMemoryIDGenerator;
import com.krishnamurti.playground.lld.trafficlight.repository.cacheimpl.IntersectionInMemoryRepository;
import com.krishnamurti.playground.lld.trafficlight.service.EmergencyRequestService;
import com.krishnamurti.playground.lld.trafficlight.service.IntersectionService;
import com.krishnamurti.playground.lld.trafficlight.service.impl.EmergencyRequestServiceImpl;
import com.krishnamurti.playground.lld.trafficlight.service.impl.IntersectionServiceImpl;

public class TrafficLightSimulation {

    public static void main(String[] args) throws InterruptedException {

        // initialize
        IDGenerator idGenerator = new InMemoryIDGenerator();
        IntersectionRepository intersectionRepository = new IntersectionInMemoryRepository();
        EmergencyRequestRepository emergencyRequestRepository = new EmergencyRequestInMemoryRepository();

        IntersectionService intersectionService = new IntersectionServiceImpl(idGenerator, intersectionRepository);
        EmergencyRequestService emergencyRequestService = new EmergencyRequestServiceImpl(idGenerator, emergencyRequestRepository, intersectionService);

        IntersectionController intersectionController = new IntersectionController(intersectionService);
        EmergencyController emergencyController = new EmergencyController(emergencyRequestService);

        Integer intersectionId = intersectionController.createIntersection();
        intersectionController.startCycle(intersectionId);

        for (int i = 0; i < 5; i++) {
            intersectionController.displayState(intersectionId);
            Thread.sleep(6000);
        }
        System.out.println("Emergency Start: ");
        emergencyController.emergencyRequest(intersectionId, Direction.WEST, 10);

        for (int i = 0; i < 30; i++) {
            intersectionController.displayState(intersectionId);
            Thread.sleep(6000);
        }

    }
}
