package com.textadventure.adventures;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.parser.ParserSystem;
import com.text_adventure.world_objects.GameWorld;
import com.text_adventure.world_objects.PossibleStates;
import com.text_adventure.world_objects.WorldDirection;
import com.text_adventure.world_objects.WorldPlayer;
import com.text_adventure.world_objects.WorldRoom;
import com.text_adventure.world_objects.WorldThing;

public class TestAdventure implements Adventure {
	public GameWorld getWorld() throws InvalidActionException {
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
		
		WorldThing key = new WorldThing(northEast, null, "key", "The magic key of Grazelphest.", false, "key", PossibleStates.NORMAL, true, false);
		
		northEast.addChild(key);
		
		world.addObjectToMap(key);
		
		// And how about a box to hold things in the north west room!
		
		// Note that our box is fixed and shouldn't be movable, and should initially be closed.
		
		WorldThing box = new WorldThing(northWest, null, "box", "A magic box of hiding stuff.", true, "box", PossibleStates.CLOSED, false, true);
		
		northWest.addChild(box);
		
		world.addObjectToMap(box);
		
		return world;
	}
}
