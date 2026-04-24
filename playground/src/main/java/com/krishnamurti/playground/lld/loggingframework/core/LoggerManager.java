package com.krishnamurti.playground.lld.loggingframework.core;

import com.krishnamurti.playground.lld.loggingframework.appenders.Appender;
import com.krishnamurti.playground.lld.loggingframework.appenders.ConsoleAppender;
import com.krishnamurti.playground.lld.loggingframework.formatters.BasicFormatter;
import com.krishnamurti.playground.lld.loggingframework.formatters.ClassNameDecorator;
import com.krishnamurti.playground.lld.loggingframework.formatters.Formatter;
import com.krishnamurti.playground.lld.loggingframework.formatters.FormatterDecorator;
import com.krishnamurti.playground.lld.loggingframework.formatters.LogLevelDecorator;
import com.krishnamurti.playground.lld.loggingframework.formatters.ThreadDecorator;
import com.krishnamurti.playground.lld.loggingframework.formatters.TimeDecorator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoggerManager {

    private static final List<Appender> appenders;
    private static final ExecutorService executorService;
    private static Formatter formatter;

    static {
        // INTERVIEW TIP: Use CopyOnWriteArrayList for Appenders. 
        // Why? In logging, we add appenders once at startup (write) 
        // but read them every time we log (many reads). COWAL is perfect for this.
        appenders = new CopyOnWriteArrayList<>();
        
        // INTERVIEW TIP: FixedThreadPool vs CachedThreadPool.
        // Fixed is safer for logging to prevent OOM if log volume spikes.
        executorService = Executors.newFixedThreadPool(3);
        
        // Decorator Pattern chain: Time -> Level -> Thread -> Class -> Message
        formatter = new TimeDecorator(new LogLevelDecorator(new ThreadDecorator(new ClassNameDecorator(new FormatterDecorator(new BasicFormatter())))));
        appenders.add(ConsoleAppender.getInstance());
    }

    private LoggerManager() {

    }

    public static void setFormatter(Formatter newFormatter) {
        formatter = newFormatter;
    }

    public static Formatter getFormatter() {
        return formatter;
    }

    public static List<Appender> getAppenders() {
        return appenders;
    }

    public static void addAppender(Appender appender) {
        appenders.add(appender);
    }

    /**
     * ASYNC DISPATCH: This is the 'Secret Sauce' for performance.
     * The business thread is NOT blocked by writing to File/DB.
     * GOTCHA: If the queue fills up, logs might be dropped or rejected.
     * Mention 'RejectedExecutionHandler' as a strategy for handling overflow.
     */
    public static void dispatch(LogMessage logMessage) {
        submitLogTask(() -> LogDispatcher.dispatchLog(appenders, logMessage));
    }

    /**
     * CLEAN SHUTDOWN: Critical for Banking.
     * Always explain how you prevent log loss during JVM shutdown.
     */
    public static boolean shutdown() throws InterruptedException {
        executorService.shutdown();
        return executorService.awaitTermination(2, TimeUnit.SECONDS);
    }

    public static void submitLogTask(Runnable runnable) {
        executorService.execute(runnable);
    }
}
