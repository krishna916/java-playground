package com.krishnamurti.playground.lld.trafficlight.service;

import com.krishnamurti.playground.lld.trafficlight.domain.Direction;

public interface EmergencyRequestService {
    Integer createEmergencyRequest(Integer intersectionId, Direction direction, Integer duration);
}
