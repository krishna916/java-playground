package com.krishnamurti.playground.lld.trafficlight.repository;

import com.krishnamurti.playground.lld.trafficlight.domain.EmergencyRequest;

import java.util.Optional;

public interface EmergencyRequestRepository {

    Integer saveOrUpdateEmergencyRequest(EmergencyRequest emergencyRequest);
    Optional<EmergencyRequest> findById(Integer emergencyRequestId);
}
