package com.krishnamurti.playground.lld.loggingframework.formatters;

import com.krishnamurti.playground.lld.loggingframework.core.LogMessage;

/**
 * INTERVIEW TIP: Use the Decorator Pattern to avoid 'Class Explosion'. 
 * Instead of 'TimeThreadClassLogger', 'TimeClassLogger', etc., 
 * we can mix and match components at runtime.
 */
public class FormatterDecorator implements Formatter {
    private Formatter formatter;

    public FormatterDecorator(Formatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Recursive call to the next decorator in the chain.
     */
    @Override
    public String formattedMessage(LogMessage logMessage) {
        return formatter.formattedMessage(logMessage);
    }

}
