package com.krishnamurti.playground.lld.trafficlight.domain.state;

import com.krishnamurti.playground.lld.trafficlight.domain.TrafficLight;
import com.krishnamurti.playground.lld.trafficlight.exception.UnsupportedTransitionException;

public class TrafficLightYellowState implements TrafficLightState {

    public static final String STATE = "YELLOW";

    private static final TrafficLightState INSTANCE = new TrafficLightYellowState();

    public static TrafficLightState getInstance() {
        return INSTANCE;
    }

    private TrafficLightYellowState() {
    }

    @Override
    public void turnOff(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightOffState.getInstance());
    }

    @Override
    public void turnGreen(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightGreenState.getInstance());
    }

    @Override
    public void turnYellow(TrafficLight trafficLight) {
        throw new UnsupportedTransitionException("Cannot be transitioned to YELLOW from YELLOW");
    }

    @Override
    public void turnRed(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightRedState.getInstance());
    }

    @Override
    public void next(TrafficLight trafficLight) {
        trafficLight.setTrafficLightState(TrafficLightRedState.getInstance());
    }

    @Override
    public boolean canTransitionTo(TrafficLightState trafficLightState) {
        return trafficLightState instanceof TrafficLightOffState
                || trafficLightState instanceof TrafficLightRedState
                || trafficLightState instanceof TrafficLightGreenState;
    }

    @Override
    public String getStateName() {
        return STATE;
    }
}
