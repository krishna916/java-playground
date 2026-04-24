package com.krishnamurti.playground.lld.trafficlight.domain.state;

import com.krishnamurti.playground.lld.trafficlight.domain.TrafficLight;

public interface TrafficLightState {

    void turnOff(TrafficLight trafficLight);
    void turnGreen(TrafficLight trafficLight);
    void turnYellow(TrafficLight trafficLight);
    void turnRed(TrafficLight trafficLight);
    void next(TrafficLight trafficLight);

    boolean canTransitionTo(TrafficLightState trafficLightState);
    String getStateName();

}
