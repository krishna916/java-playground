package com.krishnamurti.playground.lld.loggingframework.formatters;

import com.krishnamurti.playground.lld.loggingframework.core.LogMessage;

public interface Formatter {
    String formattedMessage(LogMessage message);
}
