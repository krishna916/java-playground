package com.krishnamurti.playground.lld.trafficlight.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class SignalCycle {

    public static final UUID OBJECT_ID = UUID.randomUUID();

    private Integer intersectionId;
    private Direction currentPhase;
    private boolean isPaused;
    private Direction pausedDirection;
    private LocalDateTime pausedTime;

    private volatile boolean isRunning = false;
    private volatile Integer pausedDuration;
    private EmergencyRequest emergencyRequest;

    public SignalCycle() {
    }

    public SignalCycle(int intersectionId, Direction currentPhase, boolean isPaused, Direction pausedDirection, LocalDateTime pausedTime, boolean isRunning) {
        this.intersectionId = intersectionId;
        this.currentPhase = currentPhase;
        this.isPaused = isPaused;
        this.pausedDirection = pausedDirection;
        this.pausedTime = pausedTime;
        this.isRunning = isRunning;
    }


    public EmergencyRequest getEmergencyRequest() {
        return emergencyRequest;
    }

    public void setEmergencyRequest(EmergencyRequest emergencyRequest) {
        this.emergencyRequest = emergencyRequest;
    }

    public void setIntersectionId(Integer intersectionId) {
        this.intersectionId = intersectionId;
    }

    public synchronized Integer getPausedDuration() {
        return pausedDuration;
    }

    public synchronized void setPausedDuration(Integer pausedDuration) {
        this.pausedDuration = pausedDuration;
    }

    public int getIntersectionId() {
        return intersectionId;
    }

    public void setIntersectionId(int intersectionId) {
        this.intersectionId = intersectionId;
    }

    public Direction getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Direction currentPhase) {
        this.currentPhase = currentPhase;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public Direction getPausedDirection() {
        return pausedDirection;
    }

    public void setPausedDirection(Direction pausedDirection) {
        this.pausedDirection = pausedDirection;
    }

    public LocalDateTime getPausedTime() {
        return pausedTime;
    }

    public void setPausedTime(LocalDateTime pausedTime) {
        this.pausedTime = pausedTime;
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

    public synchronized void setRunning(boolean running) {
        isRunning = running;
    }
}
