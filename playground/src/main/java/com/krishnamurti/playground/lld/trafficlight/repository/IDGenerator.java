package com.krishnamurti.playground.lld.trafficlight.repository;

import java.util.UUID;

public interface IDGenerator {
    Integer getNextId(UUID objectId);
}
