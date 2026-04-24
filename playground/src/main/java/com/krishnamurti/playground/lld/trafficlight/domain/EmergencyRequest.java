package com.krishnamurti.playground.lld.trafficlight.domain;

import java.util.UUID;

public class EmergencyRequest {

    public static final UUID OBJECT_ID = UUID.randomUUID();

    private Integer emergencyRequestId;
    private Integer intersectionId;
    private Direction direction;
    private int duration;
    private boolean isActive;

    public EmergencyRequest(int emergencyRequestId, int intersectionId, Direction direction, int duration, boolean isActive) {
        this.emergencyRequestId = emergencyRequestId;
        this.intersectionId = intersectionId;
        this.direction = direction;
        this.duration = duration;
        this.isActive = isActive;
    }

    public int getEmergencyRequestId() {
        return emergencyRequestId;
    }

    public void setEmergencyRequestId(int emergencyRequestId) {
        this.emergencyRequestId = emergencyRequestId;
    }

    public int getIntersectionId() {
        return intersectionId;
    }

    public void setIntersectionId(int intersectionId) {
        this.intersectionId = intersectionId;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
