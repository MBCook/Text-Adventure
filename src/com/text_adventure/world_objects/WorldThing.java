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
									String name, String description, boolean container, String type) {
		super(parent, children, siblings, name, description, container);
		this.type = type;
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
	 * Handle the player trying to use us with something else
	 * @param player The player
	 * @param room The room the player is in
	 * @param otherThing The thing this object is being used with
	 */
	public void handleInteraction(WorldPlayer player, WorldRoom room, WorldThing otherThing) {
		System.out.println("The player tried to use " + getName() + " with " + otherThing.getName() +
															" while in the room named " + room.getName());
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
