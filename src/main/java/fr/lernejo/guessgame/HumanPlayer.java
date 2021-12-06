package fr.lernejo.guessgame;

import fr.lernejo.logger.LoggerFactory;
import fr.lernejo.logger.Logger;

import java.util.Scanner;

public class HumanPlayer implements Player{

    private Logger logger = LoggerFactory.getLogger( "player" );
    @Override
    public Long askNextGuess() {
        Scanner scanner = new Scanner( System.in );
        System.out.print( "Number:" );
        return scanner.nextLong();
    }

    // true means that input value was lower, thus expected value is greater
    @Override
    public void respond( boolean lowerOrGreater ) {
        logger.log( "Input number is " + ( lowerOrGreater ? "lower" : "greater") + " than the number to guess" );
    }
}
