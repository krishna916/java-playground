package com.krishnamurti.playground.lld.loggingframework.core;

import com.krishnamurti.playground.lld.loggingframework.appenders.Appender;

import java.util.List;

public class LogDispatcher {


    private LogDispatcher() {}

    /**
     * THRESHOLD LOGIC: Filtering happens here.
     * 
     * GOTCHA: Passing 'appenders' as a parameter but calling 'LoggerManager.getAppenders()' 
     * inside the loop is a minor code smell. 
     * TIP: Stick to the method parameter to allow for easier testing and isolation.
     */
    public static void dispatchLog(List<Appender> appenders, LogMessage logMessage) {
        String formattedMessage = LoggerManager.getFormatter().formattedMessage(logMessage);

        for (Appender appender: appenders) {
            // Priority Check: Only log if message level >= appender threshold.
            if (appender.getLoggingLevel() <= logMessage.getLogLevel().getPriority()) {
                appender.write(formattedMessage);
            }
        }
    }
}
