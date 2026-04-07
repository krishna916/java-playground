package com.krishnamurti.playground.lld.loggingframework.formatters;

import com.krishnamurti.playground.lld.loggingframework.core.LogMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeDecorator extends FormatterDecorator {

    public TimeDecorator(Formatter formatter) {
        super(formatter);
    }

    @Override
    public String formattedMessage(LogMessage logMessage) {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + " " + super.formattedMessage(logMessage);
    }
}
