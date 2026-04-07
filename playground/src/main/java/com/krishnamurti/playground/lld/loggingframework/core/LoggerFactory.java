package com.krishnamurti.playground.lld.loggingframework.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerFactory {

    private static final Map<String, Logger> CLASS_INSTANCE_MAP;

    static {
        CLASS_INSTANCE_MAP = new ConcurrentHashMap<>();
    }

    private LoggerFactory() {
        // Private constructor ensures Singleton-like access via static methods.
    }

    /**
     * INTERVIEW TIP: Mention 'Flyweight Pattern' here. 
     * We reuse Logger instances to save memory and avoid redundant object creation.
     * 
     * GOTCHA: The 'synchronized' keyword on the method is a bottleneck. 
     * In a real interview, suggest using 'computeIfAbsent' on ConcurrentHashMap 
     * to allow lock-free reads for existing loggers.
     */
    public static synchronized Logger getLogger(String className) {
        if (CLASS_INSTANCE_MAP.containsKey(className)) {
            return CLASS_INSTANCE_MAP.get(className);
        } else {
            Logger logger = new LoggerImpl(className);
            CLASS_INSTANCE_MAP.put(className, logger);
            return logger;
        }
    }

}
