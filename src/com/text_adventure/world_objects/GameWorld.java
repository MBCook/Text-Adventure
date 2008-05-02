package com.text_adventure.world_objects;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to keep track of a few important things
 * for us that we'll be passing around to functions
 * @author mcook
 */
public class GameWorld {
	/**
	 * The room the player is currently in
	 */
	private WorldRoom room = null;
	/**
	 * The player
	 */
	private WorldPlayer player = null;
	/**
	 * A map to hold all the objects in the known universe
	 */
	private Map<String, WorldObject> nameToObjectMap = new HashMap<String, WorldObject>();
	
	/**
	 * Initialize the game world with the player in the given room
	 * @param player The player to use
	 * @param room The room they are in
	 */
	public GameWorld(WorldPlayer player, WorldRoom room) {
		this.player = player;
		this.room = room;
	}
	
	/**
	 * Set the room the player is in
	 * @param room The room to put them in
	 */
	public void setRoom(WorldRoom room) {
		this.room = room;
	}
	
	/**
	 * Get the room the player is in
	 * @return The room
	 */
	public WorldRoom getRoom() {
		return room;
	}
	
	/**
	 * Get the player
	 * @return The player
	 */
	public WorldPlayer getPlayer() {
		return player;
	}
	
	/**
	 * Add an object to the map of all objects in the universe
	 * @param object The object to keep track of
	 */
	public void addObjectToMap(WorldObject object) {
		nameToObjectMap.put(object.getName(), object);
	}
	
	/**
	 * Remove an object to the map of all objects in the universe
	 * @param object The object to forget about
	 */
	public void removeObjectFromMap(WorldObject object) {
		nameToObjectMap.remove(object.getName());
	}
	
	/**
	 * Get an object based on it's name
	 * @param name The name of the object to look for
	 * @return The object with that name
	 */
	public WorldObject getObjectByName(String name) {
		return nameToObjectMap.get(name);
	}
}
