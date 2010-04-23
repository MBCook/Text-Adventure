package com.text_adventure.world_objects;

import java.util.List;

import com.text_adventure.exception.InvalidActionException;

/**
 * A thing in our world, that's not a room
 * @author mcook
 */
public class WorldThing extends WorldObject {
	/**
	 * The type of object we are (furniture, key, whatever)
	 */
	private String type = null;
	/**
	 * If we can be moved (like a key) or not (like a big table)
	 */
	private boolean movable = true;
	/**
	 * If our state can be changed
	 */
	private boolean stateChangeable = false;
	
	/**
	 * Set us up with the things we know about
	 * @param parent Our parent
	 * @param children Our children
	 * @param name The name of this object
	 * @param description The description of this object
	 * @param container Lets us know if we are allowed to contain children
	 * @param type The type of object this is (furniture, key, whatever)
	 * @param state The state of the object
	 * @param movable If the object can be moved (like a key) or not (like a large desk)
	 * @param stateChangeable If it's possible to change the object's state (like open/close it)
	 */
	public WorldThing(WorldObject parent, List<WorldObject> children, String name,
						String description, boolean container, String type, PossibleStates state,
						boolean movable, boolean stateChangeable) {
		super(parent, children, name, description, state, container);
		this.type = type;
		this.movable = movable;
		this.stateChangeable = stateChangeable;
	}
	
	/**
	 * Find out if this object can change state
	 * @return If it can change state
	 */
	public boolean isStateChangeable() {
		return stateChangeable;
	}
	
	/**
	 * Set the type of object we are
	 * @param type The type we should be
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Set if this object can be moved
	 * @param movable If it can be moved
	 */
	public void setMovable(boolean movable) {
		this.movable = movable;
	}
	
	/**
	 * Gets the type of object we are
	 * @return The type of object we are
	 */
	public String getType() {
		return type;
	}
	
	@Override
	public void addChild(WorldObject child) throws InvalidActionException {
		if (!(child instanceof WorldThing)) {
			throw new IllegalArgumentException("WorldThings can only hold other WorldThings");
		}
		
		super.addChild(child);
	}
	
	@Override
	public boolean isRoom() {
		// We are not a room
		return false;
	}
	
	@Override
	public boolean isPlayer() {
		// We are not the player
		return false;
	}
	
	@Override
	public boolean isMovable() {
		return movable && !PossibleStates.STUCK.equals(getState());
	}
}
