package com.krishnamurti.playground.lld.trafficlight.repository.cacheimpl;

import com.krishnamurti.playground.lld.trafficlight.domain.EmergencyRequest;
import com.krishnamurti.playground.lld.trafficlight.repository.EmergencyRequestRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class EmergencyRequestInMemoryRepository implements EmergencyRequestRepository {

    private static final Map<Integer, EmergencyRequest> EMERGENCY_REQUEST_MAP = new ConcurrentHashMap<>();


    @Override
    public synchronized Integer saveOrUpdateEmergencyRequest(EmergencyRequest emergencyRequest) {
        EMERGENCY_REQUEST_MAP.put(emergencyRequest.getEmergencyRequestId(), emergencyRequest);
        return emergencyRequest.getEmergencyRequestId();
    }

    @Override
    public Optional<EmergencyRequest> findById(Integer emergencyRequestId) {
        return Optional.of(EMERGENCY_REQUEST_MAP.get(emergencyRequestId));
    }
}
