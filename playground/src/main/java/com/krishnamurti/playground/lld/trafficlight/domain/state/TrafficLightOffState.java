package com.krishnamurti.playground.lld.trafficlight.domain.state;

import com.krishnamurti.playground.lld.trafficlight.domain.TrafficLight;
import com.krishnamurti.playground.lld.trafficlight.exception.UnsupportedTransitionException;

public class TrafficLightOffState implements TrafficLightState {

    private static final String STATE = "OFF";

    private static final TrafficLightState INSTANCE = new TrafficLightOffState();

    public static TrafficLightState getInstance() {
        return INSTANCE;
    }

    private TrafficLightOffState() {
    }

    @Override
    public void turnOff(TrafficLight trafficLight) {
        throw new UnsupportedTransitionException("Cannot be transitioned to OFF from OFF");
    }

    @Override
    public void turnGreen(TrafficLight trafficLight) {
        throw new UnsupportedTransitionException("Cannot be transitioned to GREEN from OFF");
    }

    @Override
    public void turnYellow(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightYellowState.getInstance());
    }

    @Override
    public void turnRed(TrafficLight trafficLight) {
        throw new UnsupportedTransitionException("Cannot be transitioned to RED from OFF");
    }

    @Override
    public void next(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightYellowState.getInstance());
    }

    @Override
    public boolean canTransitionTo(TrafficLightState trafficLightState) {
        return trafficLightState instanceof TrafficLightYellowState;
    }

    @Override
    public String getStateName() {
        return STATE;
    }
}
