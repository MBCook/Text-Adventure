package com.text_adventure.world_objects;

import java.util.ArrayList;
import java.util.List;

import com.text_adventure.exception.InvalidActionException;

/**
 * A part of the world, which can be a room or a thing
 * @author mcook
 */
public abstract class WorldObject {
	/**
	 * The thing that owns us (null if nothing)
	 */
	private WorldObject parent = null;
	/**
	 * The things we own (null if nothing)
	 */
	private List<WorldObject> children = null;
	/**
	 * The things that are level with us (other objects in a bag, null if nothing)
	 */
	private List<WorldObject> siblings = null;
	/**
	 * The name of this object
	 */
	private String name = null;
	/**
	 * The description of this object
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
	 * @param siblings Our siblings
	 * @param name The name of this object
	 * @param description The description of this object
	 * @param container Lets us know if we are allowed to contain children
	 */
	public WorldObject(WorldObject parent, List<WorldObject> children, List<WorldObject> siblings,
							String name, String description, boolean container) {
		this.parent = parent;
		this.children = children;
		this.siblings = siblings;
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
	 * Add a sibling to the list of our siblings
	 * @param sibling The sibling to add
	 */
	public void addSibling(WorldObject sibling) {
		if (siblings == null)
			siblings = new ArrayList<WorldObject>();
		if (!siblings.contains(sibling))
			siblings.add(sibling);
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
	 * Remove a sibling object from the list of sibling objects
	 * @param sibling The sibling to remove
	 */
	public void removeSibling(WorldObject sibling) {
		if (siblings == null)
			return;
		siblings.remove(sibling);
	}
	
	/**
	 * Get a list of our sibling objects
	 * @return A list of the objects
	 */
	public List<WorldObject> getSiblings() {
		return siblings;
	}
	
	/**
	 * Answer if this object is a room or not
	 * @return If we are a room or not
	 */
	public abstract boolean isRoom();
}
