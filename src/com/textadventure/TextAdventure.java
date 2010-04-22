package com.textadventure;

import java.util.ArrayList;
import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.PlayerDeathException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserHelper;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.parser.verbs.GameVerb;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.PossibleStates;
import com.textadventure.adventures.TestAdventure;

/**
 * A test class I can use to make sure things are jiving as I'd like
 * @author mcook
 */
public class TextAdventure {
	/**
	 * Does everything we could ever want, and then some
	 * @param args The arguments given to the program
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		GameWorld world = new TestAdventure().getWorld();
		
		// Now start the main loop!
		
		mainLoop(world);
	}
	
	/**
	 * The main loop of the game
	 * @param world The world to operate in
	 * @throws Exception
	 */
	private static void mainLoop(GameWorld world) throws Exception {
		// Theory:
		//
		// 1. Print out where the player is
		// 2. Get a line of input
		// 3. Parse it
		// 4. Run the command
		// 5. Repeat ad naseum
		
		System.out.println("You being to exist in a room.");
		
		// Der Loop
		
		while (PossibleStates.ALIVE.equals(world.getPlayer().getState())) {
			// Print a simple prompt
			
			String sentence = ParserHelper.readLine("? ", false);
			
			// Parse into bits
			
			List<ParserToken> tokens = null;
			
			try {
				tokens = world.getParser().parseSentence(world, sentence);
			} catch (UnknownObjectException e) {
				System.out.println(e.getMessage());
				
				continue;	// Try things again
			}
			
			// See if that grammar makes any sense to us
			
			try {
				world.getParser().checkGrammar(tokens);
			} catch (InvalidGrammarException e) {
				System.out.println(e.getMessage());
				
				continue;	// Try things again
			}
			
			// OK, it made sense, execute it
			
			GameVerb verb = (GameVerb) tokens.get(0);	// Get the verb from the sentence
			
			try {
				verb.executeVerb(world, tokens);
			} catch (Exception e) {
				if ((e instanceof InvalidActionException) || (e instanceof InvalidGrammarException)
						|| (e instanceof UnknownObjectException) || (e instanceof GamestateChangeException)) {
					System.out.println(e.getMessage());
					
					if (e instanceof PlayerDeathException) {
						// If they died, mark them as such
						world.getPlayer().setState(PossibleStates.DEAD);
					}
				} else {
					// Some kind of real exception we weren't expecting
					
					throw e;
				}
			}
		}
		
		// Print out their final status. We have to fake a sentence to do this legally 
		
		System.out.println("");	// Just some spacing so things look nicer
		
		GameVerb statusVerb = world.getParser().getVerbFromWord("status");
		
		List<ParserToken> fakeSentence = new ArrayList<ParserToken>();
		
		fakeSentence.add(statusVerb);
		
		statusVerb.executeVerb(world, fakeSentence);
	}
}
