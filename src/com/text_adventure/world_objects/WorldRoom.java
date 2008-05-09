package com.text_adventure.world_objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.exception.PlayerDeathException;

/**
 * Represents a room in the game world
 * @author mcook
 */
public class WorldRoom extends WorldObject {
	/**
	 * The rooms that we are attached to
	 */
	private Map<WorldDirection, WorldRoom> roomExits = new HashMap<WorldDirection, WorldRoom>();
	
	/**
	 * Set us up with the things we know about
	 * @param parent Our parent
	 * @param children Our children
	 * @param name The name of this object
	 * @param description The description of this object
	 */
	public WorldRoom(WorldObject parent, List<WorldObject> children,
												String name, String description) {
		super(parent, children, name, description, true);
	}
	
	/**
	 * Gets the room off to the given direction
	 * @param direction The direction we are looking at
	 * @return The {@link WorldRoom}
	 */
	public WorldRoom getRoomInDirection(WorldDirection direction) {
		return roomExits.get(direction);
	}
	
	/**
	 * Store the room that should be to our west
	 * @param room The {@link WorldRoom}
	 * @param direction The direction this other room is in
	 */
	public void setRoomInDirection(WorldRoom room, WorldDirection direction) {
		roomExits.put(direction, room);
	}

	/**
	 * A funciton called when the player tries to enter this room
	 * @throws InvalidActionException When they try to do something they shouldn't (i.e. door is locked)
	 * @throws PlayerDeathException When they do something stupid (i.e. killed by guillotine over door)
	 */
	public void attemptToEnter() throws InvalidActionException, PlayerDeathException {
		// Nothing
	}
	
	/**
	 * A funciton called when the player tries to exit this room
	 * @throws InvalidActionException When they try to do something they shouldn't (i.e. chained to floor)
	 * @throws PlayerDeathException When they do something stupid (i.e. trigger land-mine)
	 */
	public void attemptToExit() throws InvalidActionException, PlayerDeathException {
		// Nothing
	}
	
	public boolean isRoom() {
		// We are a room
		return true;
	}
	
	public boolean isPlayer() {
		// We are not a player
		return false;
	}
	
	public boolean isMoveable() {
		// We can't be picked up and moved
		return false;
	}
}
