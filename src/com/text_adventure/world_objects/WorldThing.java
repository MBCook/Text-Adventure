package com.text_adventure.world_objects;

import java.util.List;

/**
 * A thing in our world, that's not a room
 * @author mcook
 */
public class WorldThing extends WorldObject {
	/**
	 * The type of object we are (furnature, key, whatever)
	 */
	public String type = null;
	/**
	 * If we can be moved (like a key) or not (like a big table)
	 */
	public boolean moveable = true;
	
	/**
	 * Set us up with the things we know about
	 * @param parent Our parent
	 * @param children Our children
	 * @param name The name of this object
	 * @param description The description of this object
	 * @param container Lets us know if we are allowed to contain children
	 * @param type The type of object this is (furnature, key, whatever)
	 */
	public WorldThing(WorldObject parent, List<WorldObject> children, String name,
						String description, boolean container, String type, PossibleStates state, boolean moveable) {
		super(parent, children, name, description, state, container);
		this.type = type;
		this.moveable = moveable;
	}
	
	/**
	 * Set the type of object we are
	 * @param type The type we should be
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the type of object we are
	 * @return The type of object we are
	 */
	public String getType() {
		return type;
	}
	
	public boolean isRoom() {
		// We are not a room
		return false;
	}
	
	public boolean isPlayer() {
		// We are not the player
		return false;
	}
	
	public boolean isMoveable() {
		return moveable && !PossibleStates.STUCK.equals(getState());
	}
}
