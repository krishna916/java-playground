package com.krishnamurti.playground.lld.loggingframework.appenders;

public class ConsoleAppender implements Appender {

    private static final Appender INSTANCE = new ConsoleAppender();
    
    // INTERVIEW TIP: 'volatile' ensures that if the threshold is changed 
    // by one thread (e.g., Admin UI), all worker threads see it immediately.
    private volatile int loggingLevel;

    public static Appender getInstance() {
        return INSTANCE;
    }

    private ConsoleAppender() {}

    /**
     * INTERVIEW TIP: 'synchronized' here prevents logs from different 
     * threads from being interleaved (mixed up) in the console output.
     */
    @Override
    public synchronized void write(String message) {
        System.out.println(message);
    }

    public void setLoggingLevel(int loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    @Override
    public int getLoggingLevel() {
        return this.loggingLevel;
    }
}
