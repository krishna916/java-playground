package com.krishnamurti.playground.lld.trafficlight.repository.cacheimpl;

import com.krishnamurti.playground.lld.trafficlight.domain.EmergencyRequest;
import com.krishnamurti.playground.lld.trafficlight.domain.Intersection;
import com.krishnamurti.playground.lld.trafficlight.domain.SignalCycle;
import com.krishnamurti.playground.lld.trafficlight.domain.SignalTiming;
import com.krishnamurti.playground.lld.trafficlight.domain.TrafficLight;
import com.krishnamurti.playground.lld.trafficlight.repository.IDGenerator;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryIDGenerator implements IDGenerator {

    private static final ConcurrentHashMap<UUID, Integer> OBJECT_ID_MAP = new ConcurrentHashMap<>();

    private static final IDGenerator INSTANCE = new InMemoryIDGenerator();

    static {
        OBJECT_ID_MAP.put(Intersection.OBJECT_ID, 0);
        OBJECT_ID_MAP.put(SignalCycle.OBJECT_ID, 0);
        OBJECT_ID_MAP.put(SignalTiming.OBJECT_ID, 0);
        OBJECT_ID_MAP.put(TrafficLight.OBJECT_ID, 0);
        OBJECT_ID_MAP.put(EmergencyRequest.OBJECT_ID, 0);
    }

    public static IDGenerator getInstance() {
        return INSTANCE;
    }

    public synchronized Integer getNextId(UUID objectId) {
        if (!OBJECT_ID_MAP.containsKey(objectId)) {
            throw new IllegalArgumentException("Invalid objectId");
        }

        return OBJECT_ID_MAP.compute(objectId, ((uuid, integer) -> integer + 1));
    }

}
