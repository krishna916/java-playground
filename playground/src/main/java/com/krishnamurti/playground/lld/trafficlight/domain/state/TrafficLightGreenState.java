package com.krishnamurti.playground.lld.trafficlight.domain.state;

import com.krishnamurti.playground.lld.trafficlight.domain.TrafficLight;
import com.krishnamurti.playground.lld.trafficlight.exception.UnsupportedTransitionException;

public class TrafficLightGreenState implements TrafficLightState {

    public static final String STATE = "GREEN";

    private static final TrafficLightState INSTANCE = new TrafficLightGreenState();

    public static TrafficLightState getInstance() {
        return INSTANCE;
    }

    private TrafficLightGreenState() {
    }

    @Override
    public void turnOff(TrafficLight trafficLight) {
        throw new UnsupportedTransitionException("Cannot be transitioned to OFF from GREEN");
    }

    @Override
    public void turnGreen(TrafficLight trafficLight) {
        throw new UnsupportedTransitionException("Cannot be transitioned to GREEN from GREEN");
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
