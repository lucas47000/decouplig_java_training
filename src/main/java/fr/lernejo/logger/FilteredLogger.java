package fr.lernejo.logger;

import java.util.function.Predicate;

public class FilteredLogger implements Logger {

    private Logger delegateLogger;
    private Predicate<String> condition;

    public  FilteredLogger( Logger logger, Predicate<String> condition ) {
        this.delegateLogger = logger;
        this.condition = condition;
    }

    @Override
    public void log( String message ) {
        if ( condition.test( message ) ) {
            delegateLogger.log( message );
        }
    }
}