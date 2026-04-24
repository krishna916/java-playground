package com.krishnamurti.playground.lld.trafficlight.domain;

import com.krishnamurti.playground.lld.trafficlight.domain.state.TrafficLightState;

import java.util.UUID;

public class TrafficLight {

    public static final UUID OBJECT_ID = UUID.randomUUID();

    private Integer trafficLightId;
    private Direction direction;
    private SignalTiming signalTiming;
    private TrafficLightState trafficLightState;

    public TrafficLight() {
    }

    public TrafficLight(int trafficLightId, Direction direction, SignalTiming signalTiming, TrafficLightState trafficLightState) {
        this.trafficLightId = trafficLightId;
        this.direction = direction;
        this.signalTiming = signalTiming;
        this.trafficLightState = trafficLightState;
    }

    public int getTrafficLightId() {
        return trafficLightId;
    }

    public void setTrafficLightId(int trafficLightId) {
        this.trafficLightId = trafficLightId;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public SignalTiming getSignalTiming() {
        return signalTiming;
    }

    public void setSignalTiming(SignalTiming signalTiming) {
        this.signalTiming = signalTiming;
    }

    public TrafficLightState getTrafficLightState() {
        return trafficLightState;
    }

    public void setTrafficLightState(TrafficLightState trafficLightState) {
        this.trafficLightState = trafficLightState;
    }

    public void cycleState() {
        this.trafficLightState.next(this);
    }

    public void turnYellow() {
        this.trafficLightState.turnYellow(this);
    }

    public void turnRed() {
        this.trafficLightState.turnRed(this);
    }
}
