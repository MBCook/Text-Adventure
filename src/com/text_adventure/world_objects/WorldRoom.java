package com.text_adventure.world_objects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	private Set<WorldObject> specialObjects = new HashSet<WorldObject>();
	
	/**
	 * Set us up with the things we know about
	 * @param parent Our parent
	 * @param children Our children
	 * @param name The name of this object
	 * @param description The description of this object
	 * @param state The current state of the room
	 */
	public WorldRoom(WorldObject parent, List<WorldObject> children,
												String name, String description, PossibleStates state) {
		super(parent, children, name, description, state, true);
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
	 * A function called when the player tries to enter this room
	 * @throws InvalidActionException When they try to do something they shouldn't (i.e. door is locked)
	 * @throws PlayerDeathException When they do something stupid (i.e. killed by guillotine over door)
	 */
	public void attemptToEnter() throws InvalidActionException, PlayerDeathException {
		if (PossibleStates.LOCKED.equals(this.getState())) {
			throw new InvalidActionException("The room is locked.");
		}
		
		// It's OK unless a subclass says otherwise
	}
	
	/**
	 * A function called when the player tries to exit this room
	 * @throws InvalidActionException When they try to do something they shouldn't (i.e. chained to floor)
	 * @throws PlayerDeathException When they do something stupid (i.e. trigger land-mine)
	 */
	public void attemptToExit() throws InvalidActionException, PlayerDeathException {
		if (PossibleStates.LOCKED.equals(this.getState())) {
			throw new InvalidActionException("The room is locked.");
		}
		
		// Nothing
	}
	
	/**
	 * Tells us if the object is special (the description talks about it) or not (just on the floor)
	 * @param thing The object to find out about
	 * @return The answer
	 */
	public boolean isObjectSpecialToThisRoom(WorldObject thing) {
		return specialObjects.contains(thing);
	}
	
	/**
	 * Add an object to the list of special objects we know about
	 * @param thing The thing to add
	 */
	public void addSpecialObject(WorldObject thing) {
		specialObjects.add(thing);
	}
	
	/**
	 * Remove an object from the list of special objects we know about
	 * @param thing The thing to remove
	 */
	public void removeSpecialObject(WorldObject thing) {
		specialObjects.remove(thing);
	}
	
	@Override
	public boolean isRoom() {
		// We are a room
		return true;
	}
	
	@Override
	public boolean isPlayer() {
		// We are not a player
		return false;
	}
	
	@Override
	public boolean isMovable() {
		// We can't be picked up and moved
		return false;
	}
}
