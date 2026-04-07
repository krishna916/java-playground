package com.krishnamurti.playground.lld.loggingframework.appenders;

public class DBAppender implements Appender{
    private volatile int loggingLevel;

    private static final Appender INSTANCE = new DBAppender();

    public static Appender getInstance() {
        return INSTANCE;
    }

    private DBAppender() {

    }

    @Override
    public synchronized void write(String message) {
        System.out.println("[Appended to DB] " + message);
    }

    public void setLoggingLevel(int loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    @Override
    public int getLoggingLevel() {
        return this.loggingLevel;
    }
}
