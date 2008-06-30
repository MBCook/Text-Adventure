package com.textadventure;

import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserHelper;
import com.text_adventure.parser.ParserSystem;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.parser.verbs.GameVerb;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.WorldDirection;
import com.text_adventure.world_objects.WorldPlayer;
import com.text_adventure.world_objects.WorldRoom;

/**
 * A test class I can use to make sure things are jiving as I'd like
 * 
 * @author mcook
 */
public class TextAdventure {
	/**
	 * Does everything we could ever want, and then some
	 * @param args The arguments given to the program
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		// Let's create a little grid of rooms.
		
		WorldRoom northWest = new WorldRoom(null, null, "NorthWestRoom", "The room in the north west corner of the house. You see hideous drapes and a worn avacado colored carpet.");
		WorldRoom northEast = new WorldRoom(null, null, "NorthEastRoom", "The room in the north east corner of the house. There are cobwebs all over the place.");
		WorldRoom southWest = new WorldRoom(null, null, "SouthWestRoom", "The room in the south west corner of the house. You notice an odd odor you can't identify.");
		WorldRoom southEast = new WorldRoom(null, null, "SouthEastRoom", "The room in the south east corner of the house. It's quite cold. You'd think a room with southern exposure would get more sun.");
		
		northWest.setRoomInDirection(southWest, WorldDirection.SOUTH);
		northWest.setRoomInDirection(northEast, WorldDirection.EAST);
		
		northEast.setRoomInDirection(southEast, WorldDirection.SOUTH);
		northEast.setRoomInDirection(northWest, WorldDirection.WEST);
		
		southEast.setRoomInDirection(northEast, WorldDirection.NORTH);
		southEast.setRoomInDirection(southWest, WorldDirection.WEST);
		
		southWest.setRoomInDirection(southEast, WorldDirection.EAST);
		southWest.setRoomInDirection(northWest, WorldDirection.NORTH);
		
		// Now create a player
		
		WorldPlayer player = new WorldPlayer(null, null, "Yourself", "Yourself", true);

		// The parser system
		
		ParserSystem parser = new ParserSystem();
		
		// Now a game world to hold everything
		
		GameWorld world = new GameWorld(player, southWest, parser);
		
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
		
		while (true) {
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
					System.out.print("\n");
					System.out.println(e.getMessage());
					
					break;	
				} else {
					// Some kind of real exception we weren't expecting
					
					throw e;
				}
			}
		}
		
		System.out.println("\nExiting...");
	}
}
