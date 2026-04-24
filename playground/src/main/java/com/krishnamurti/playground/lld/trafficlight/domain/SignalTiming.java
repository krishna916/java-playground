package com.krishnamurti.playground.lld.trafficlight.domain;

import java.util.UUID;

public class SignalTiming {

    public static final UUID OBJECT_ID = UUID.randomUUID();

    private Integer signalTimingId;
    private int greenDuration;
    private int yellowDuration;
    private Integer trafficLightId;
    private Integer intersectionId;

    public SignalTiming() {
        // initialize default values
        this.greenDuration = 10;
        this.yellowDuration = 3;
    }

    public SignalTiming(Integer intersectionId, Integer trafficLightId, Integer signalTimingId) {
        this();
        this.intersectionId = intersectionId;
        this.trafficLightId = trafficLightId;
        this.signalTimingId = signalTimingId;
    }

    public SignalTiming(int signalTimingId, int greenDuration, int yellowDuration, int trafficLightId, int intersectionId) {
        this.signalTimingId = signalTimingId;
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;
        this.trafficLightId = trafficLightId;
        this.intersectionId = intersectionId;
    }

    public int getSignalTimingId() {
        return signalTimingId;
    }

    public void setSignalTimingId(int signalTimingId) {
        this.signalTimingId = signalTimingId;
    }

    public int getGreenDuration() {
        return greenDuration;
    }

    public int getGreenDurationInMillis() {
        return greenDuration * 1000;
    }

    public void setGreenDuration(int greenDuration) {
        this.greenDuration = greenDuration;
    }

    public int getYellowDuration() {
        return yellowDuration;
    }

    public int getYellowDurationInMillis() {
        return yellowDuration * 1000;
    }

    public void setYellowDuration(int yellowDuration) {
        this.yellowDuration = yellowDuration;
    }

    public int getTrafficLightId() {
        return trafficLightId;
    }

    public void setTrafficLightId(int trafficLightId) {
        this.trafficLightId = trafficLightId;
    }

    public int getIntersectionId() {
        return intersectionId;
    }

    public void setIntersectionId(int intersectionId) {
        this.intersectionId = intersectionId;
    }
}
