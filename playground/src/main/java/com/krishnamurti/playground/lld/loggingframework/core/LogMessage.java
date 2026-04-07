package com.krishnamurti.playground.lld.loggingframework.core;

public class LogMessage {

    private String className;
    private LogLevel logLevel;
    private String message;
    private String threadName;

    public static LogMessageBuilder builder() {
        return new LogMessageBuilder();
    }

    public LogMessage(LogMessageBuilder builder) {
        this.className = builder.className;
        this.logLevel = builder.logLevel;
        this.message = builder.message;
        this.threadName = builder.threadName;
    }

    public String getClassName() {
        return className;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return threadName;
    }

    public static class LogMessageBuilder {
        private String className;
        private LogLevel logLevel;
        private String message;
        private String threadName;

        public LogMessageBuilder className(String className) {
            this.className = className;
            return this;
        }

        public LogMessageBuilder logLevel(LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public LogMessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public LogMessageBuilder threadName(String threadName) {
            this.threadName = threadName;
            return this;
        }

        public LogMessage build() {
            return new LogMessage(this);
        }
    }
}
