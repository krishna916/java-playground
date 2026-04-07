package com.krishnamurti.playground.lld.loggingframework.core;

import com.krishnamurti.playground.lld.loggingframework.appenders.Appender;

public class LoggerImpl implements Logger {

    private final String loggingClass;

    public LoggerImpl(String className) {
        this.loggingClass = className;
    }

    // Standard Log Level methods. 
    // TIP: In an interview, mention that these are convenience methods 
    // that wrap the core 'log' method to follow the DRY (Don't Repeat Yourself) principle.

    @Override
    public  void debug(String message) {
        this.log(LogLevel.DEBUG, message);
    }

    @Override
    public  void info(String message) {
        this.log(LogLevel.INFO, message);
    }

    @Override
    public  void warn(String message) {
        this.log(LogLevel.WARN, message);
    }

    @Override
    public  void error(String message) {
        this.log(LogLevel.ERROR, message);
    }

    @Override
    public  void fatal(String message) {
        this.log(LogLevel.FATAL, message);
    }


    /**
     * CORE LOGIC: Converts a simple string into a rich 'LogMessage' object.
     * 
     * GOTCHA: This method captures 'Thread.currentThread().getName()'. 
     * In high-frequency logging, this call is relatively cheap, 
     * but 'LoggerManager.dispatch' is the part that must be non-blocking.
     */
    private  void log(LogLevel logLevel, String message) {
        LogMessage logMessage = LogMessage.builder()
                .message(message)
                .logLevel(logLevel)
                .className(loggingClass)
                .threadName(Thread.currentThread().getName())
                .build();
        LoggerManager.dispatch(logMessage);
    }
}
