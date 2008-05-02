package com.text_adventure.world_objects;

import java.util.List;

import com.text_adventure.exception.InvalidActionException;

/**
 * Represents a room in the game world
 * @author mcook
 */
public class WorldRoom extends WorldObject {
	/**
	 * The room that is to the north of this one
	 */
	private WorldRoom northRoom = null;
	/**
	 * The room that is to the east of this one
	 */
	private WorldRoom eastRoom = null;
	/**
	 * The room that is to the south of this one
	 */
	private WorldRoom southRoom = null;
	/**
	 * The room that is to the west of this one
	 */
	private WorldRoom westRoom = null;
	
	/**
	 * Set us up with the things we know about
	 * @param parent Our parent
	 * @param children Our children
	 * @param name The name of this object
	 * @param description The description of this object
	 */
	public WorldRoom(WorldObject parent, List<WorldObject> children,
												String name, String description) {
		super(parent, children, null, name, description, true);
	}
	
	/**
	 * Gets the room to our east
	 * @return The {@link WorldRoom}
	 */
	public WorldRoom getEastRoom() {
		return eastRoom;
	}

	/**
	 * Store the room that should be to our east
	 * @param eastRoom The {@link WorldRoom}
	 */
	public void setEastRoom(WorldRoom eastRoom) {
		this.eastRoom = eastRoom;
	}

	/**
	 * Gets the room to our north
	 * @return The {@link WorldRoom}
	 */
	public WorldRoom getNorthRoom() {
		return northRoom;
	}

	/**
	 * Store the room that should be to our north
	 * @param northRoom The {@link WorldRoom}
	 */
	public void setNorthRoom(WorldRoom northRoom) {
		this.northRoom = northRoom;
	}

	/**
	 * Gets the room to our south
	 * @return The {@link WorldRoom}
	 */
	public WorldRoom getSouthRoom() {
		return southRoom;
	}

	/**
	 * Store the room that should be to our south
	 * @param southRoom The {@link WorldRoom}
	 */
	public void setSouthRoom(WorldRoom southRoom) {
		this.southRoom = southRoom;
	}

	/**
	 * Gets the room to our west
	 * @return The {@link WorldRoom}
	 */
	public WorldRoom getWestRoom() {
		return westRoom;
	}

	/**
	 * Store the room that should be to our west
	 * @param westRoom The {@link WorldRoom}
	 */
	public void setWestRoom(WorldRoom westRoom) {
		this.westRoom = westRoom;
	}

	public boolean isRoom() {
		return true;
	}
	
	public void addSibling(WorldObject sibling) throws InvalidActionException {
		// The user should never see this, it's for development only
		throw new InvalidActionException("Rooms can't be given siblings.");
	}
}
