package fr.lernejo.guessgame;

import fr.lernejo.logger.LoggerFactory;

public class ComputerPlayer implements Player {

    private Boolean lastGuessLowerOrGreater = null;
    private Long lowerBound = Simulation.LOWER_BOUND;
    private Long upperBound = Simulation.UPPER_BOUND;

    @Override
    public Long askNextGuess() {
        long nextGuess;
        if ( lastGuessLowerOrGreater != null ) {
            if ( lastGuessLowerOrGreater ) {
                this.lowerBound = ( this.upperBound + this.lowerBound ) / 2;
            } else {
                this.upperBound = ( this.upperBound + this.lowerBound ) / 2;
            }
        }
        nextGuess = ( this.upperBound + this.lowerBound ) / 2;
        return nextGuess;
    }

    // true means that input value was lower, thus expected value is greater
    @Override
    public void respond( boolean lowerOrGreater ) {
        LoggerFactory.getLogger( "player" ).log( lowerOrGreater ? "Greater" : "Lower" );
        lastGuessLowerOrGreater = lowerOrGreater;
    }
}
