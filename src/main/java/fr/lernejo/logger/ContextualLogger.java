package fr.lernejo.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContextualLogger implements Logger {

    private String callerClass;
    private Logger delegateLogger;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss.SSS" );

    public ContextualLogger( String className, Logger logger) {
        this.callerClass = className;
        this.delegateLogger = logger;
    }

    @Override
    public void log( String message ) {
        delegateLogger.log( LocalDateTime.now().format( formatter ) + " " + callerClass + " " + message );
    }
}
