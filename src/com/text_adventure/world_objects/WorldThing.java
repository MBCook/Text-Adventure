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
	 * The state of this object
	 */
	public String state = null;
	
	/**
	 * Set us up with the things we know about
	 * @param parent Our parent
	 * @param children Our children
	 * @param siblings Our siblings
	 * @param name The name of this object
	 * @param description The description of this object
	 * @param container Lets us know if we are allowed to contain children
	 * @param type The type of object this is (furnature, key, whatever)
	 */
	public WorldThing(WorldObject parent, List<WorldObject> children, List<WorldObject> siblings,
							String name, String description, boolean container, String type, String state) {
		super(parent, children, siblings, name, description, container);
		this.type = type;
		this.state = state;
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
	
	/**
	 * Set the state of this object
	 * @param state The state this object should be in
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isPlayer() {
		return false;
	}
	
	public boolean supportsSiblings() {
		return true;
	}
}
