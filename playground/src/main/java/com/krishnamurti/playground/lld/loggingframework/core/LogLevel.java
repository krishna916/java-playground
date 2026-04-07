package com.krishnamurti.playground.lld.loggingframework.core;

public enum LogLevel {

    DEBUG("DEBUG", 0),
    INFO("INFO", 1),
    WARN("WARN", 2),
    ERROR("ERROR", 3),
    FATAL("FATAL", 4)
    ;

    private final String level;
    private final int priority;

    LogLevel(String level, int priority) {
        this.level = level;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public String getLevel() {
        return level;
    }
}
