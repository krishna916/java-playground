package com.krishnamurti.playground.lld.loggingframework.formatters;

import com.krishnamurti.playground.lld.loggingframework.core.LogMessage;

public class BasicFormatter implements Formatter{
    @Override
    public String formattedMessage(LogMessage logMessage) {
        return logMessage.getMessage();
    }
}
