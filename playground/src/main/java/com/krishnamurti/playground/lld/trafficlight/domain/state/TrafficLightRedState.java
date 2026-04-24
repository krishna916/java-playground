package com.krishnamurti.playground.lld.trafficlight.domain.state;

import com.krishnamurti.playground.lld.trafficlight.domain.TrafficLight;
import com.krishnamurti.playground.lld.trafficlight.exception.UnsupportedTransitionException;

public class TrafficLightRedState implements TrafficLightState {

    public static final String STATE = "RED";

    private static final TrafficLightState INSTANCE = new TrafficLightRedState();

    public static TrafficLightState getInstance() {
        return INSTANCE;
    }

    private TrafficLightRedState() {

    }

    @Override
    public void turnOff(TrafficLight trafficLight) {
        throw new UnsupportedTransitionException("Cannot be transitioned to OFF from RED");
    }

    @Override
    public void turnGreen(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightGreenState.getInstance());
    }

    @Override
    public void turnYellow(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightYellowState.getInstance());
    }

    @Override
    public void turnRed(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightRedState.getInstance());
    }

    @Override
    public void next(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightGreenState.getInstance());
    }

    @Override
    public boolean canTransitionTo(TrafficLightState trafficLightState) {
        return trafficLightState instanceof TrafficLightGreenState;
    }

    @Override
    public String getStateName() {
        return STATE;
    }
}
