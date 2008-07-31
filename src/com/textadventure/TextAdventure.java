package com.textadventure;

import java.util.ArrayList;
import java.util.List;

import com.text_adventure.exception.GamestateChangeException;
import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.InvalidGrammarException;
import com.text_adventure.exception.PlayerDeathException;
import com.text_adventure.exception.UnknownObjectException;
import com.text_adventure.parser.ParserHelper;
import com.text_adventure.parser.ParserSystem;
import com.text_adventure.parser.ParserToken;
import com.text_adventure.parser.verbs.GameVerb;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.PossibleStates;
import com.text_adventure.world_objects.WorldDirection;
import com.text_adventure.world_objects.WorldPlayer;
import com.text_adventure.world_objects.WorldRoom;
import com.text_adventure.world_objects.WorldThing;

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
		// Let's create a little grid of rooms.
		
		WorldRoom northWest = new WorldRoom(null, null, "NorthWestRoom", "The room in the north west corner of the house. You see hideous drapes and a worn avacado colored carpet.", PossibleStates.NORMAL);
		WorldRoom northEast = new WorldRoom(null, null, "NorthEastRoom", "The room in the north east corner of the house. There are cobwebs all over the place.", PossibleStates.NORMAL);
		WorldRoom southWest = new WorldRoom(null, null, "SouthWestRoom", "The room in the south west corner of the house. You notice an odd odor you can't identify.", PossibleStates.NORMAL);
		WorldRoom southEast = new WorldRoom(null, null, "SouthEastRoom", "The room in the south east corner of the house. It's quite cold. You'd think a room with southern exposure would get more sun.", PossibleStates.NORMAL);
		
		northWest.setRoomInDirection(southWest, WorldDirection.SOUTH);
		northWest.setRoomInDirection(northEast, WorldDirection.EAST);
		
		northEast.setRoomInDirection(southEast, WorldDirection.SOUTH);
		northEast.setRoomInDirection(northWest, WorldDirection.WEST);
		
		southEast.setRoomInDirection(northEast, WorldDirection.NORTH);
		southEast.setRoomInDirection(southWest, WorldDirection.WEST);
		
		southWest.setRoomInDirection(southEast, WorldDirection.EAST);
		southWest.setRoomInDirection(northWest, WorldDirection.NORTH);
		
		// Now create a player
		
		WorldPlayer player = new WorldPlayer(null, null, "Yourself", "Yourself", PossibleStates.ALIVE, true);

		// The parser system
		
		ParserSystem parser = new ParserSystem();
		
		// Now a game world to hold everything
		
		GameWorld world = new GameWorld(player, southWest, parser);
		
		// Now we'll make an object and put it in the north east room, and register it with the world
		
		WorldThing key = new WorldThing(northEast, null, "key", "The magic key of Grazelphest.", false, "key", PossibleStates.NORMAL, true);
		
		northEast.addChild(key);
		
		world.addObjectToMap(key);
		
		// And how about a box to hold things in the north west room!
		
		// Note that our box is fixed and shouldn't be moveable, and should initially be closed.
		
		WorldThing box = new WorldThing(northWest, null, "box", "A magic box of hiding stuff.", true, "box", PossibleStates.CLOSED, false);
		
		northWest.addChild(box);
		
		world.addObjectToMap(box);
		
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
