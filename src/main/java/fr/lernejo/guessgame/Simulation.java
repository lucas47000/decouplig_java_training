package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Simulation {

    public static final Long LOWER_BOUND = 0L;
    public static final Long UPPER_BOUND = 100L;
    private final Logger logger = LoggerFactory.getLogger( "simulation" );
    private final Player player;
    private Long numberToGuess;

    public Simulation( Player player ) {
        this.player = player;
    }

    public void initialize( long numberToGuess ) {
        if ( numberToGuess < LOWER_BOUND  || UPPER_BOUND < numberToGuess ) {
            throw new IllegalArgumentException( "The given number is outside of authorized bounds" );
        }
        this.numberToGuess = numberToGuess;
    }

    /**
     * @return true if the player has guessed the right number
     */
    private boolean nextRound() {
        Long inputNumber = player.askNextGuess();
        logger.log( "Input number is " + inputNumber );
        if ( inputNumber < numberToGuess ) {
            //logger.log( "Input number is lower than the number to guess" );
            player.respond( true );
        } else if ( inputNumber > numberToGuess ) {
            //logger.log( "Input number is greater than the number to guess" );
            player.respond( false );
        } else if ( inputNumber == numberToGuess ) {
            logger.log( "Input number is equal to the number to guess" );
            return true;
        }
        return false;
    }

    public void loopUntilPlayerSucceed( long turns ) {
        long count = 0;
        boolean found; //defaults to false
        long startingTime = System.currentTimeMillis();
        do {
            count++;
            found = nextRound();
            if ( ! found ) {
                logger.log( "Failed attempt. " + count +" failed attempts" );
            }
        } while ( count < turns && ! found );
        if ( count == turns ) {
            logger.log( "Number not found after " + turns + " attempts" );
        } else {
            logger.log( "Number found after " + count + " attempts" );
        }
        long gameTimeMillis = System.currentTimeMillis() - startingTime;
        String gameTime = String.format( "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours( gameTimeMillis ),
            TimeUnit.MILLISECONDS.toMinutes( gameTimeMillis ) - TimeUnit.HOURS.toMinutes( TimeUnit.MILLISECONDS.toHours( gameTimeMillis ) ),
            TimeUnit.MILLISECONDS.toSeconds( gameTimeMillis ) - TimeUnit.MINUTES.toSeconds( TimeUnit.MILLISECONDS.toMinutes( gameTimeMillis ) ) );
        logger.log( ( found ? "Game won" : "Game lose" ) + " - Game took: " + gameTime );
    }
}
