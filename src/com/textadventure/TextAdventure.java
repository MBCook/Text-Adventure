package com.textadventure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.UnknownObjectException;
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
		
		WorldRoom northWest = new WorldRoom(null, null, "NorthWestRoom", "The room in the north west corner");
		WorldRoom northEast = new WorldRoom(null, null, "NorthEastRoom", "The room in the north east corner");
		WorldRoom southWest = new WorldRoom(null, null, "SouthWestRoom", "The room in the south west corner");
		WorldRoom southEast = new WorldRoom(null, null, "SouthEastRoom", "The room in the south east corner");
		
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
		
		// Now a game world to hold everything
		
		GameWorld world = new GameWorld(player, southWest);
		
		// Now start the main loop!
		
		mainLoop(world);
	}
	
	/**
	 * The main loop of the game
	 * @param world The world to operate in
	 * @throws Exception
	 */
	private static void mainLoop(GameWorld world) throws Exception {
		ParserSystem parser = new ParserSystem();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
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
			
			System.out.print("? ");
			
			// Get a line of input
			
			String sentence = in.readLine();
			
			// Parse into bits
			
			List<ParserToken> tokens = null;
			
			try {
				tokens = parser.parseSentence(world, sentence);
			} catch (UnknownObjectException e) {
				System.out.println(e.getMessage());
				
				continue;	// Try things again
			}
			
			// See if that grammar makes any sense to us
			
			try {
				parser.checkGrammar(tokens);
			} catch (InvalidGrammarException e) {
				System.out.println(e.getMessage());
				
				continue;	// Try things again
			}
			
			// OK, it made sense, execute it
			
			GameVerb verb = (GameVerb) tokens.get(0);	// Get the verb from the sentence
			
			try {
				verb.executeVerb(world, tokens);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
				break;
			}
		}
		
		System.out.println("\n\nExiting...");
	}
}