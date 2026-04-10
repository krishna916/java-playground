package com.krishnamurti.playground.lld.loggingframework;

import com.krishnamurti.playground.lld.loggingframework.appenders.Appender;
import com.krishnamurti.playground.lld.loggingframework.appenders.DBAppender;
import com.krishnamurti.playground.lld.loggingframework.appenders.FileAppender;
import com.krishnamurti.playground.lld.loggingframework.core.LogLevel;
import com.krishnamurti.playground.lld.loggingframework.core.Logger;
import com.krishnamurti.playground.lld.loggingframework.core.LoggerFactory;
import com.krishnamurti.playground.lld.loggingframework.core.LoggerManager;

import java.io.IOException;

public class LoggerSimulation {

    public static void main(String[] args) throws IOException, InterruptedException {
        Logger logger = LoggerFactory.getLogger("MainClass");
        Appender fileAppender = FileAppender.getInstance();
        fileAppender.setLoggingLevel(LogLevel.WARN.getPriority());
        LoggerManager.addAppender(fileAppender);

        logger.warn("Warning!!!!");
        logger.debug("Debug!!!");
        logger.fatal("Fatal!!!!");
        logger.error("error!!!!");
        logger.info("Info!!!!");

        Logger logger1 = LoggerFactory.getLogger("MainClassTest");
        Appender dbAppender =DBAppender.getInstance();
        dbAppender.setLoggingLevel(LogLevel.ERROR.getPriority());
        LoggerManager.addAppender(dbAppender);

        logger1.warn("Warning!!!!");
        logger1.debug("Debug!!!");
        logger1.fatal("Fatal!!!!");
        logger1.error("error!!!!");
        logger1.info("Info!!!!");

        LoggerManager.shutdown();
    }
}
