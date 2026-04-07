package com.krishnamurti.playground.lld.loggingframework.formatters;

import com.krishnamurti.playground.lld.loggingframework.core.LogMessage;

public class ThreadDecorator extends FormatterDecorator{
    public ThreadDecorator(Formatter formatter) {
        super(formatter);
    }

    @Override
    public String formattedMessage(LogMessage logMessage) {
        return "[" + logMessage.getThreadName()+"] " + super.formattedMessage(logMessage);
    }
}
