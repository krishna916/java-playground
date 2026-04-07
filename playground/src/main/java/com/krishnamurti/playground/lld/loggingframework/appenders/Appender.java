package com.krishnamurti.playground.lld.loggingframework.appenders;

public interface Appender {
    void write(String message);
    int getLoggingLevel();
    void setLoggingLevel(int loggingLevel);
}
