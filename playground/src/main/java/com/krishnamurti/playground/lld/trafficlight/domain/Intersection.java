package com.krishnamurti.playground.lld.trafficlight.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Intersection extends Thread {

    public static final UUID OBJECT_ID = UUID.randomUUID();

    private Integer intersectionId;
    private Map<Direction, TrafficLight> directionTrafficLightMap;
    private volatile boolean isEmergencyMode;
    private SignalCycle signalCycle;
    private ExecutorService executorService;

    public Intersection() {
        executorService = Executors.newSingleThreadExecutor();
    }



    public Intersection(int intersectionId, Map<Direction, TrafficLight> directionTrafficLightMap, boolean isEmergencyMode, SignalCycle signalCycle) {
        this.intersectionId = intersectionId;
        this.directionTrafficLightMap = directionTrafficLightMap;
        this.isEmergencyMode = isEmergencyMode;
        this.signalCycle = signalCycle;
    }

    public Integer getIntersectionId() {
        return intersectionId;
    }

    public void setIntersectionId(Integer intersectionId) {
        this.intersectionId = intersectionId;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public Map<Direction, TrafficLight> getDirectionTrafficLightMap() {
        return directionTrafficLightMap;
    }

    public void setDirectionTrafficLightMap(Map<Direction, TrafficLight> directionTrafficLightMap) {
        this.directionTrafficLightMap = directionTrafficLightMap;
    }

    public synchronized boolean isEmergencyMode() {
        return isEmergencyMode;
    }

    public synchronized void setEmergencyMode(boolean emergencyMode) {
        isEmergencyMode = emergencyMode;
    }

    public SignalCycle getSignalCycle() {
        return signalCycle;
    }

    public void setSignalCycle(SignalCycle signalCycle) {
        this.signalCycle = signalCycle;
    }

    @Override
    public void run() {
        while (true) {
            this.signalCycle.setRunning(true);


            for (Direction direction : Direction.values()) {
                TrafficLight trafficLight = this.directionTrafficLightMap.get(direction);
                trafficLight.cycleState();
            }

            while (this.signalCycle.isRunning()) {
                for (Direction currentDirection : Direction.values()) {
                    try {
                        if (this.signalCycle.getPausedDirection() != null && this.signalCycle.getPausedDirection() != currentDirection) {
                            continue;
                        }
                        this.signalCycle.setPausedDirection(null);
                        this.signalCycle.setPaused(false);
                        this.signalCycle.setCurrentPhase(currentDirection);
                        TrafficLight currentTrafficLight = this.directionTrafficLightMap.get(currentDirection);
                        SignalTiming signalTiming = currentTrafficLight.getSignalTiming();


                        currentTrafficLight.cycleState();
                        System.out.println("IntersectionId" + intersectionId + ", Direction: " + currentDirection.name() +
                                ", TrafficLightChanged: " + currentTrafficLight.getTrafficLightState().getStateName());
                        Thread.sleep(signalTiming.getGreenDurationInMillis());

                        currentTrafficLight.cycleState();
                        System.out.println("IntersectionId" + intersectionId + ", Direction: " + currentDirection.name() +
                                ", TrafficLightChanged: " + currentTrafficLight.getTrafficLightState().getStateName());
                        Thread.sleep(signalTiming.getYellowDurationInMillis());

                        currentTrafficLight.cycleState();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            while (this.isEmergencyMode) {
                try {
                    System.out.println("Emergency cycle start");
                    this.signalCycle.setCurrentPhase(this.signalCycle.getPausedDirection());
                    TrafficLight currentTrafficLight = this.directionTrafficLightMap.get(this.signalCycle.getPausedDirection());
                    currentTrafficLight.cycleState();

                    Thread.sleep(this.signalCycle.getPausedDuration() * 1000);

                    currentTrafficLight.cycleState();
                    Thread.sleep(currentTrafficLight.getSignalTiming().getYellowDurationInMillis());

                    currentTrafficLight.cycleState();
                    System.out.println("Emergency cycle end");
                    this.isEmergencyMode = false;
                    this.signalCycle.getEmergencyRequest().setActive(false);
                    this.signalCycle.setPaused(false);
                    this.signalCycle.setRunning(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void handleEmergency(EmergencyRequest emergencyRequest) {
        this.signalCycle.setPaused(true);
        this.signalCycle.setPausedDuration(emergencyRequest.getDuration());
        this.signalCycle.setPausedDirection(emergencyRequest.getDirection());
        this.signalCycle.setPausedTime(LocalDateTime.now());
        this.signalCycle.setRunning(false);
        this.signalCycle.setEmergencyRequest(emergencyRequest);


        try {
            Thread.sleep(1000);
            for (Direction d: Direction.values()) {
                TrafficLight trafficLight = this.directionTrafficLightMap.get(d);
                trafficLight.turnRed();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
