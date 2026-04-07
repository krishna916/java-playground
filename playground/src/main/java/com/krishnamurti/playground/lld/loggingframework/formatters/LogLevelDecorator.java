package com.krishnamurti.playground.lld.loggingframework.formatters;

import com.krishnamurti.playground.lld.loggingframework.core.LogMessage;

public class LogLevelDecorator extends FormatterDecorator {

    public LogLevelDecorator(Formatter formatter) {
        super(formatter);
    }

    @Override
    public String formattedMessage(LogMessage logMessage) {
        return logMessage.getLogLevel().getLevel() +  " " + super.formattedMessage(logMessage);
    }
}
