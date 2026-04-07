package com.krishnamurti.playground.lld.loggingframework.appenders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender{

    private File logFile = null;
    private volatile int loggingLevel;

    private static final Appender INSTANCE = new FileAppender();

    public static Appender getInstance() {
        return INSTANCE;
    }

    private FileAppender()  {

    }


    @Override
    public synchronized void write(String message) {
        System.out.println("[File Appender] " + message);
    }

    public void setLoggingLevel(int loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    @Override
    public int getLoggingLevel() {
        return this.loggingLevel;
    }
}
