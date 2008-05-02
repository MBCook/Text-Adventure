package com.text_adventure.world_objects;

import java.util.ArrayList;
import java.util.List;

import com.text_adventure.exception.InvalidActionException;

/**
 * A part of the world, which can be a room or a thing
 * @author mcook
 */
public abstract class WorldObject implements Comparable<WorldObject> {
	/**
	 * The thing that owns us (null if nothing)
	 */
	private WorldObject parent = null;
	/**
	 * The things we own (null if nothing)
	 */
	private List<WorldObject> children = null;
	/**
	 * The name of this object, which must be unique in the universe
	 */
	private String name = null;
	/**
	 * The description of this object. This can contain some special characters.
	 * %s refers to the state of this object, %n refers to it's name,
	 * and %c is the number of things it contains.
	 */
	private String description = null;
	/**
	 * Can we hold other objects
	 */
	private boolean container = false;

	/**
	 * Set us up with the things we know about
	 * @param parent Our parent
	 * @param children Our children
	 * @param name The name of this object
	 * @param description The description of this object
	 * @param container Lets us know if we are allowed to contain children
	 */
	public WorldObject(WorldObject parent, List<WorldObject> children,
							String name, String description, boolean container) {
		this.parent = parent;
		this.children = children;
		this.name = name;
		this.description = description;
		this.container = container;
	}
	
	/**
	 * Return whether or not we can contain things
	 * @return Wheter or not we can contain things
	 */
	public boolean isContainer() {
		return container;
	}
	
	/**
	 * Get our name
	 * @return Our name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get our description
	 * @return Our description
	 */
	public String getDescription() {
		String desc = description;
		
		desc = desc.replace("%s", getState());
		desc = desc.replace("%c", "" + (children == null ? 0 : children.size()));
		desc = desc.replace("%n", getName());
		
		return description;
	}
	
	/**
	 * Get our parent object
	 * @return Our parent object
	 */
	public WorldObject getParent() {
		return parent;
	}
	
	/**
	 * Set a new parent object for us
	 * @param parent The new parent we will use
	 */
	public void setParent(WorldObject parent) {
		this.parent = parent;
	}
	
	/**
	 * Get a list of our children
	 * @return The list of our children
	 */
	public List<WorldObject> getChildren() {
		return children;
	}
	
	/**
	 * Add a child to the list of our children
	 * @param child The child to add
	 * @throws InvalidActionException if you try to do something you shouldn't
	 */
	public void addChild(WorldObject child) throws InvalidActionException {
		if (!container)
			throw new InvalidActionException("A " + name + " can't hold things");
		if (children == null)
			children = new ArrayList<WorldObject>();
		if (!children.contains(child))
			children.add(child);
	}
	
	/**
	 * Remove a child object from the list of child objects
	 * @param child The child to remove
	 * @throws InvalidActionException if you try to do something you shouldn't
	 */
	public void removeChild(WorldObject child) throws Exception {
		if (!container)
			throw new InvalidActionException("A " + name + " can't hold things");
		if (children == null)
			return;
		children.remove(child);
	}

	/**
	 * Compare this object to another (for sorting purposes)
	 * @param other The object to compare to
	 * @return The result of the comparison
	 */
	public int compareTo(WorldObject other) {
		return getName().compareTo(other.getName());
	}
	
	/**
	 * Checks if this object is equal to another boject
	 * @param other The other object
	 * @return If we are equal
	 */
	public boolean equals(WorldObject other) {
		return getName().equals(other.getName());
	}
	
	/**
	 * Get the current state of this object
	 */
	public String getState() {
		return null;
	}
	
	/**
	 * Answer if this object is a room or not
	 * @return If we are a room or not
	 */
	public abstract boolean isRoom();
	
	/**
	 * Answer if this object is the player or not
	 * @return If we are the player or not
	 */
	public abstract boolean isPlayer();
	
	/**
	 * Whether or not this item can be moved (i.e. picked up)
	 * @return If we are moveable
	 */
	public abstract boolean isMoveable();
}
