package com.krishnamurti.playground.lld.trafficlight.service.impl;

import com.krishnamurti.playground.lld.trafficlight.domain.Direction;
import com.krishnamurti.playground.lld.trafficlight.domain.Intersection;
import com.krishnamurti.playground.lld.trafficlight.domain.SignalCycle;
import com.krishnamurti.playground.lld.trafficlight.domain.SignalTiming;
import com.krishnamurti.playground.lld.trafficlight.domain.TrafficLight;
import com.krishnamurti.playground.lld.trafficlight.domain.state.TrafficLightOffState;
import com.krishnamurti.playground.lld.trafficlight.repository.IDGenerator;
import com.krishnamurti.playground.lld.trafficlight.repository.IntersectionRepository;
import com.krishnamurti.playground.lld.trafficlight.service.IntersectionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IntersectionServiceImpl implements IntersectionService {

    private final IDGenerator idGenerator;
    private final IntersectionRepository intersectionRepository;


    public IntersectionServiceImpl(final IDGenerator idGenerator, final IntersectionRepository intersectionRepository) {
        this.idGenerator = idGenerator;
        this.intersectionRepository = intersectionRepository;
    }

    @Override
    public Integer addIntersection(List<Direction> directions) {
        Integer intersectionId = idGenerator.getNextId(Intersection.OBJECT_ID);

        Map<Direction, TrafficLight> trafficLightMap = generateDirectionTrafficLightMap(intersectionId);


        SignalCycle cycle = new SignalCycle(intersectionId, Direction.NORTH, false, null, null, false);

        Intersection intersection = new Intersection(intersectionId, trafficLightMap, false, cycle);

        return intersectionRepository.addIntersection(intersection);
    }

    @Override
    public Intersection getById(Integer intersectionId) {
        Optional<Intersection> optionalIntersection = intersectionRepository.findById(intersectionId);
        if (optionalIntersection.isEmpty()) {
            throw new IllegalArgumentException("Invalid intersectionId provided");
        }

        return optionalIntersection.get();
    }

    @Override
    public boolean startCycle(Integer intersectionId) {
        Intersection intersection = getById(intersectionId);
        if (intersection.getSignalCycle().isRunning()) {
            throw new IllegalStateException("Signal cycle is already running for this intersection: " + intersectionId);
        }

        intersection.getDirectionTrafficLightMap().forEach((k, v) -> v.turnYellow());

        intersection.start();
        return true;
    }

    @Override
    public void displayCurrentState(Integer intersectionId) {
        System.out.println("\n\n\n");
        Intersection intersection = getById(intersectionId);
        Map<Direction, TrafficLight> trafficLightMap = intersection.getDirectionTrafficLightMap();
        for (Direction direction: Direction.values()) {
            TrafficLight trafficLight = trafficLightMap.get(direction);
            System.out.println(direction.name() + ": " + trafficLight.getTrafficLightState().getStateName());
        }
    }


    private Map<Direction, TrafficLight> generateDirectionTrafficLightMap(Integer intersectionId) {
        Map<Direction, TrafficLight> trafficLightMap = new HashMap<>();

        for (Direction direction: Direction.values()) {
            Integer trafficLightId = idGenerator.getNextId(TrafficLight.OBJECT_ID);
            Integer signalTimingId = idGenerator.getNextId(SignalTiming.OBJECT_ID);

            SignalTiming signalTiming = new SignalTiming(intersectionId, trafficLightId, signalTimingId);

            TrafficLight trafficLight = new TrafficLight(trafficLightId, direction, signalTiming, TrafficLightOffState.getInstance());

            trafficLightMap.put(direction, trafficLight);
        }
        return trafficLightMap;
    }
}
