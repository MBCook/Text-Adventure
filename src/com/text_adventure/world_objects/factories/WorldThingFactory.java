package com.text_adventure.world_objects.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.text_adventure.exception.InvalidActionException;
import com.text_adventure.world_objects.PossibleStates;
import com.text_adventure.world_objects.WorldObject;
import com.text_adventure.world_objects.WorldThing;

/**
 * A class to make building WorldThings much easier, by chaining calls. Instead of
 * having to create the object and then call 40 different setters, this class allows
 * objects to be built with lines such as:
 * 
 * WorldThingFactory.create("a","b",C).makeUnmovable().makeContainer().addChildren(list).finish();
 * 
 * @author mcook
 */
public class WorldThingFactory {
	/**
	 * The children our WorldThing will have
	 */
	private ArrayList<WorldThing> children = new ArrayList<WorldThing>();
	/**
	 * The name our WorldThing will have
	 */
	private String name;
	/**
	 * The description our WorldThing will have
	 */
	private String description;
	/**
	 * The state our WorldThing will have
	 */
	private PossibleStates state;
	/**
	 * The parent our WorldThing will have
	 */
	private WorldObject parent;
	/**
	 * The type our WorldThing will have
	 */
	private String type;
	/**
	 * Whether our WorldThing will be a container
	 */
	private boolean container;
	/**
	 * Wheter our WorldThing will be movable
	 */
	private boolean movable;
	
	/**
	 * Start making an object, with the given starting parameters
	 * @param name The name of the object we're making
	 * @param description The description the object should hold
	 * @param state The state of the new object
	 * @return A WorldThingFactory that can be used chained to build an object
	 */
	public static WorldThingFactory create(String name, String description, PossibleStates state) {
		return new WorldThingFactory(name, description, state);
	}
	
	/**
	 * Initialize us with some stuff that every object we'll create will need,
	 * and seed the object we're making with the little bit of data we know, and the defaults
	 * (no parent, no children, type "object", not a container, movable).
	 * @param name The name of the object we're making
	 * @param description The description the object should hold
	 * @param state The state of the new object
	 */
	private WorldThingFactory(String name, String description, PossibleStates state) {
		this.name = name;
		this.description = description;
		this.state = state;
		this.parent = null;
		this.type = "object";
		this.container = false;
		this.movable = true;
	}
	
	/**
	 * Create the actual object, and return it
	 * @return The WorldThing we made
	 * @throws InvalidActionException If the given parent can't hold children
	 */
	public WorldThing finish() throws InvalidActionException {
		// You can't call a function that expects Type<X> passing Type<XSubClass>, because
		// it would be too easy to allow that cast. We'll just have to do it the old fashioned way.
		
		List<WorldObject> stuff = new ArrayList<WorldObject>();
		
		stuff.addAll(children);
		
		// Create the object
		
		WorldThing thing = new WorldThing(parent, stuff, name, description,
											container, type, state, movable);
		
		// Register it with the parent (if given)
		
		if (parent != null)
			parent.addChild(thing);
		
		return thing;
	}
	
	/**
	 * Add the given children to the thing we're creating
	 * @param children A collection of children to add
	 * @return This factory
	 * @throws InvalidActionException When the thing we're making can't hold children
	 */
	public WorldThingFactory addChildren(Collection<WorldThing> children) throws InvalidActionException {
		this.children.addAll(children);
		
		return this;
	}
	
	/**
	 * Add the given children to the thing we're creating
	 * @param children An array of children to add
	 * @return This factory
	 * @throws InvalidActionException When the thing we're making can't hold children
	 */
	public WorldThingFactory addChildren(WorldThing children[]) throws InvalidActionException {
		for (WorldThing child : children)
			this.children.add(child);
		
		return this;
	}

	/**
	 * Mark the thing we're making as a container
	 * @return This factory
	 */
	public WorldThingFactory makeContainer() {
		container = true;
		
		return this;
	}
	
	/**
	 * Mark the thing we're making as movable
	 * @return This factory
	 */
	public WorldThingFactory makeUnmovable() {
		movable = false;
		
		return this;
	}

	/**
	 * Mark who our object's parent should be
	 * @return This factory
	 */
	public WorldThingFactory setParent(WorldObject parent) {
		this.parent = parent;
		
		return this;
	}

	/**
	 * Mark what the type of our new object should be
	 * @return This factory
	 */
	public WorldThingFactory setType(String type) {
		this.type = type;
		
		return this;
	}
}
