package com.text_adventure.world_objects;

import java.util.ArrayList;
import java.util.List;

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

	public WorldObject(WorldObject parent, List<WorldObject> children, List<WorldObject> siblings) {
		this.parent = parent;
		this.children = children;
		this.siblings = siblings;
	}
	
	public WorldObject getParent() {
		return parent;
	}
	
	public List<WorldObject> getChildren() {
		return children;
	}
	
	public void addChild(WorldObject child) {
		if (children == null)
			children = new ArrayList<WorldObject>();
		if (!children.contains(child))
			children.add(child);
	}
	
	public void addSibling(WorldObject sibling) {
		if (siblings == null)
			siblings = new ArrayList<WorldObject>();
		if (!siblings.contains(sibling))
			siblings.add(sibling);
	}
	
	public void removeChild(WorldObject child) {
		if (children == null)
			return;
		children.remove(child);
	}
	
	public void removeSibling(WorldObject sibling) {
		if (siblings == null)
			return;
		siblings.remove(sibling);
	}
	
	public List<WorldObject> getSiblings() {
		return siblings;
	}
	
	public abstract boolean isRoom();
}
