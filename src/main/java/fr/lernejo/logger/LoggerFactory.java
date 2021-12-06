package fr.lernejo.logger;

public class LoggerFactory {
    public static Logger getLogger( String name ) {
        FileLogger fileLogger = new FileLogger( "./runLogs.log" );
        FilteredLogger filteredLogger = new FilteredLogger( fileLogger, message -> message.contains( "simulation" ) );
        CompositeLogger compositeLogger = new CompositeLogger( new ConsoleLogger(), filteredLogger );
        return new ContextualLogger( name, compositeLogger );
    }
}
