package com.krishnamurti.playground.lld.loggingframework.formatters;

import com.krishnamurti.playground.lld.loggingframework.core.LogMessage;

public class ClassNameDecorator extends FormatterDecorator{

    public ClassNameDecorator(Formatter formatter) {
        super(formatter);
    }

    @Override
    public String formattedMessage(LogMessage logMessage) {
        return logMessage.getClassName() +
                " " +
                super.formattedMessage(logMessage);
    }
}
