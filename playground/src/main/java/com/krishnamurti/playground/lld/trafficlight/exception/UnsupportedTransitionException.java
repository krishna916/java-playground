package com.krishnamurti.playground.lld.trafficlight.exception;

public class UnsupportedTransitionException extends UnsupportedOperationException{

    public UnsupportedTransitionException(String message) {
        super(message);
    }

    public UnsupportedTransitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
