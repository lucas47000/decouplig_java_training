package fr.lernejo.guessgame;

import java.security.SecureRandom;

public class Launcher {
    public static void main( String[] args ) {
    	//MODIF PART 4 (if ...)
        if ( args.length > 0 && args[0].equals( "-interactive" ) ) {
            HumanPlayer myHumanPlayer = new HumanPlayer();
            Simulation mySimulation = new Simulation( myHumanPlayer );
            SecureRandom random = new SecureRandom();
            long randomNumber = random.nextLong( Simulation.LOWER_BOUND, Simulation.UPPER_BOUND );
            mySimulation.initialize( randomNumber );
            mySimulation.loopUntilPlayerSucceed( Long.MAX_VALUE );
        } else if ( args.length > 1 && args[0].equals( "-auto" ) ) {
            ComputerPlayer myComputerPlayer = new ComputerPlayer();
            Simulation mySimulation = new Simulation( myComputerPlayer );
            long numberToGuess = Long.parseLong( args[1] );
            mySimulation.initialize( numberToGuess );
            mySimulation.loopUntilPlayerSucceed( 1000 );
        } else {
            System.out.println( "You must run the Launcher either with \"-interactive\" or \"-auto <number-of-turn>\" arguments" );
        }
    }
}
