package fr.lernejo.logger;

public class LoggerFactory {
    public static Logger getLogger( String name ) {
    	//MODIF PART 5
    	//MODIF PART 6
    	//Modif part 7
        FileLogger fileLogger = new FileLogger( "./runLogs.log" );
        FilteredLogger filteredLogger = new FilteredLogger( fileLogger, message -> message.contains( "simulation" ) );
        CompositeLogger compositeLogger = new CompositeLogger( new ConsoleLogger(), filteredLogger );
        return new ContextualLogger( name, compositeLogger );
    }
}
